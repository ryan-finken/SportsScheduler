package org.launchcode.Play4All;

import org.launchcode.Play4All.Controllers.AuthenticationController;
import org.launchcode.Play4All.data.UserRepository;
import org.launchcode.Play4All.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> blacklist = Arrays.asList("/user", "/venue", "/event");

    private static boolean notBlackListed(String path){
        for (String pathRoot : blacklist){
            if (path.startsWith(pathRoot)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        if (notBlackListed(request.getRequestURI())){
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);


        if (user != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
