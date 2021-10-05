package org.launchcode.Play4All.Controllers;


import org.launchcode.Play4All.data.EventRepository;
import org.launchcode.Play4All.data.UserRepository;
import org.launchcode.Play4All.data.VenueRepository;
import org.launchcode.Play4All.models.Event;
import org.launchcode.Play4All.models.User;
import org.launchcode.Play4All.models.Venue;
import org.launchcode.Play4All.models.dto.UserEventDTO;
import org.launchcode.Play4All.models.dto.VenueEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayEvents(@RequestParam(required = false) Integer eventId, Model model) {

        if (eventId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("event", eventRepository.findAll());
        } else {
            Optional<Event> result = eventRepository.findById(eventId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + eventId);
            } else {
                Event event = result.get();
                model.addAttribute("title", "Event in Venue: " + event.getName());
                model.addAttribute("event", event.getDescription());
            }
        }

        return "event/index";
    }


    @GetMapping("create")
    public String displayCreateEventForm(@RequestParam Integer venueId, Model model) {
        Optional<Venue> result = venueRepository.findById(venueId);
        VenueEventDTO venueEvent = new VenueEventDTO();
        venueEvent.setVenue(result.get());
        venueEvent.setEvent(new Event());
        model.addAttribute("venueEvent", venueEvent);
        return "event/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute VenueEventDTO venueEvent, Model model) {
        Venue venue = venueEvent.getVenue();
        Event event = venueEvent.getEvent();
        venue.addEvent(event);
        eventRepository.save(event);
        venueRepository.save(venue);
        return "redirect:";
    }

    @GetMapping("details")
    public String displayUserDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("event", event);

        return "event/details";
    }
    @GetMapping("edit-event")
    public String displayEditBioForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("event", event);

        return "event/edit-event";

    }

    @PostMapping("edit-event")
    public String processEditBioForm(@RequestParam int id, @RequestParam String description) {

        Optional<Event> result = eventRepository.findById(id);
        Event event = result.get();
        event.setDescription(description);
        eventRepository.save(event);

        return "redirect:details?eventId=" + id;
    }

    @GetMapping("register")
    public String displayRegisterForm(@RequestParam Integer eventId, @RequestParam Integer userId){
        Optional<Event> eventResult = eventRepository.findById(eventId);
        Event event = eventResult.get();
        Optional<User> userResult = userRepository.findById(userId);
        User user = userResult.get();
        if (!event.getUsers().contains(user)) {
            event.addUser(user);
            eventRepository.save(event);
        }

        return "redirect:details?eventId=" + eventId;
    }

}
