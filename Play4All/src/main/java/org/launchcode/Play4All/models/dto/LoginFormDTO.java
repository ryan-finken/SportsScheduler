package org.launchcode.Play4All.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {
    @NotNull
    @NotBlank
    @Size(min=3, max=20, message= "Invalid username. Must be between 3 and 20 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min=5, max=30, message= "Invalid password. Must be between 5 and 30 characters")
    private String password;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username= username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
