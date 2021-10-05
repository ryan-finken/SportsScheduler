package org.launchcode.Play4All.Controllers;


import org.launchcode.Play4All.data.VenueRepository;
import org.launchcode.Play4All.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("create")
    public String displayCreateVenueForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "venue/create";
    }

    @PostMapping("create")
    public String processCreateVenueForm(@ModelAttribute Venue venue) {
        venueRepository.save(venue);
        return "redirect:";
    }

}
