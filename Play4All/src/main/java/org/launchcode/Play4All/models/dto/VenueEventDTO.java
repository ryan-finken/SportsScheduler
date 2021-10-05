package org.launchcode.Play4All.models.dto;

import org.launchcode.Play4All.models.Event;
import org.launchcode.Play4All.models.Venue;

public class VenueEventDTO {

    private Venue venue;

    private Event event;

    public VenueEventDTO() {
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
