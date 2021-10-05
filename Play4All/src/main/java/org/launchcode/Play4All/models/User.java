package org.launchcode.Play4All.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{
    @NotNull
    private String username;

    @Email
    private String email;

    @NotNull
    private String pwHash;

    private String bio = "";

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @ManyToMany(mappedBy = "users")
    private List<Event> events = new ArrayList<>();

    public User(){}

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername(){
        return username;
    }

    public String getDisplayName(){
        return username + " ";
    }

    public String getEmail(){ return email; }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvent(List<Event> eventList) {
        this.events = eventList;
    }
}
