package org.launchCode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping
    public String displayAllEvents(Model model){
        List<String> events = new ArrayList<>();
        events.add("Boot Camp");
        events.add("Meet up");
        events.add("Launch code");
        model.addAttribute("events",events);
        return "events/index";
    }
    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
    
}
