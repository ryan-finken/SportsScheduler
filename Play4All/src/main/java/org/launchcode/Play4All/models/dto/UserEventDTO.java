package org.launchcode.Play4All.models.dto;

import org.launchcode.Play4All.models.Event;
import org.launchcode.Play4All.models.User;

import javax.validation.constraints.NotNull;

public class UserEventDTO {

    @NotNull
    private Event event;

    @NotNull
    private User user;

    public UserEventDTO (){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
