package org.launchCode.codingevents.controllers;

import org.launchCode.codingevents.data.EventData;
import org.launchCode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title","Create Event");
        model.addAttribute("event",new Event());
        return "events/create";
    }

    //lives at /events/create -->post method
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors,Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title","Create Event");
            //model.addAttribute("errorMsg","Invalid Entries! Please check!");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
    @GetMapping("edit/{eventId}")
    public String RenderEditEventForm(Model model,@PathVariable int eventId){
        Event eventToBeEdited = EventData.getById(eventId);
        model.addAttribute("event",eventToBeEdited);
        String heading = "Edit Event: "+ eventToBeEdited.getName()+"(ID:"+eventToBeEdited.getId()+")";
        model.addAttribute("title",heading);
        return "events/edit";
    }
    @PutMapping("edit")
    public String editEvent(@RequestParam int eventId,@RequestParam String name,@RequestParam String description){
//        Event eventToBeEdited = EventData.getById(eventId);
//        eventToBeEdited.setName(name);
//        eventToBeEdited.setDescription(description);
        EventData.edit(eventId,name,description);
        return "redirect:";
    }
}
