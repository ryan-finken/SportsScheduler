package org.launchcode.Play4All.Controllers;


import org.launchcode.Play4All.data.VenueRepository;
import org.launchcode.Play4All.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("venue")
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping
    public String displayAllVenues(Model model) {
        model.addAttribute("venues", venueRepository.findAll());
        return "venue/index";
    }

    @GetMapping("details")
    public String displayVenue(@RequestParam Integer venueId, Model model) {
        Optional<Venue> result = venueRepository.findById(venueId);
        Venue venue = result.get();
        model.addAttribute("venue", venue);
        return "venue/details";
    }

}
