package org.launchCode.codingevents.controllers;

import org.launchCode.codingevents.data.EventCategoryRepository;
import org.launchCode.codingevents.data.EventRepository;
import org.launchCode.codingevents.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title","Create Event");
        model.addAttribute("event",new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    //lives at /events/create -->post method
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors,Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title","Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }
    @GetMapping("edit/{eventId}")
    public String RenderEditEventForm(Model model,@PathVariable int eventId){
        Event eventToBeEdited = eventRepository.findById(eventId).get();
        model.addAttribute("event",eventToBeEdited);
        String heading = "Edit Event: "+ eventToBeEdited.getName()+"(ID:"+eventToBeEdited.getId()+")";
        model.addAttribute("title",heading);
        return "events/edit";
    }
    @PutMapping("edit")
    public String editEvent(@ModelAttribute @Valid Event event,int eventId,Errors errors,Model model){
        if(errors.hasErrors()){
            Event eventToBeEdited = eventRepository.findById(eventId).get();
            model.addAttribute("event",eventToBeEdited);
            String heading = "Edit Event: "+ eventToBeEdited.getName()+"(ID:"+eventToBeEdited.getId()+")";
            model.addAttribute("title",heading);
            return "events/edit";
        }
        Event eventToBeEdited = eventRepository.findById(eventId).get();
        eventToBeEdited.setName(event.getName());
        eventToBeEdited.setDescription(event.getDescription());
        eventToBeEdited.setContactEmail(event.getContactEmail());
        eventToBeEdited.setLocation(event.getLocation());
        eventToBeEdited.setNumberOfAttendees(event.getNumberOfAttendees());

        eventRepository.save(eventToBeEdited);
        return "redirect:";
    }
}
