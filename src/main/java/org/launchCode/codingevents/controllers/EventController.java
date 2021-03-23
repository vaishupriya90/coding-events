package org.launchCode.codingevents.controllers;

import org.launchCode.codingevents.data.EventCategoryRepository;
import org.launchCode.codingevents.data.EventRepository;
import org.launchCode.codingevents.data.TagRepository;
import org.launchCode.codingevents.models.Event;
import org.launchCode.codingevents.models.EventCategory;
import org.launchCode.codingevents.models.EventDetails;
import org.launchCode.codingevents.models.Tag;
import org.launchCode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if(categoryId == null) {
            model.addAttribute("title","All Events");
            model.addAttribute("events", eventRepository.findAll());
        }else{
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Event Category ID: "+ categoryId);
            }else{
                EventCategory category = result.get();
                model.addAttribute("title","Events in category: " + category.getName());
                model.addAttribute("events",category.getEvents());
            }
        }
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
        eventToBeEdited.getEventDetails().setDescription(event.getEventDetails().getDescription());
        eventToBeEdited.getEventDetails().setContactEmail(event.getEventDetails().getContactEmail());
        eventToBeEdited.getEventDetails().setLocation(event.getEventDetails().getLocation());
        eventToBeEdited.getEventDetails().setNumberOfAttendees(event.getEventDetails().getNumberOfAttendees());

        eventRepository.save(eventToBeEdited);
        return "redirect:";
    }

    @GetMapping("details/{eventId}")
    public String eventDetails(@PathVariable int eventId,Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
         EventDetails eventDetails = event.getEventDetails();
         model.addAttribute("event",event);
        model.addAttribute("eventDetails",eventDetails);
        return "events/eventDetails";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title","Add Tag for Event: " + event.getName());
        model.addAttribute("event",event);
        model.addAttribute("tags",tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag",eventTag);
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,Model model,Errors errors){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
                return "redirect:details/"+event.getId();
            }
        }
        return "redirect:add-tag";
    }
}