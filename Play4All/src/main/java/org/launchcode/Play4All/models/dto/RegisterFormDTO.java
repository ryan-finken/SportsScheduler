package org.launchcode.Play4All.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegisterFormDTO extends LoginFormDTO{


    //@Email
    //private String email;

    private String verifyPassword;

    //public String getEmail() {
    //    return email;
   // }

   // public void setEmail(String email) {
    //    this.email = email;
   // }

    public String getVerifyPassword(){
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
