package org.launchCode.codingevents.controllers;

import org.launchCode.codingevents.data.EventCategoryRepository;
import org.launchCode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEventCategories(Model model){
        Iterable<EventCategory> categories = eventCategoryRepository.findAll();
        model.addAttribute("title","All Categories");
        model.addAttribute("categories",categories);
        return"eventCategory/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute("eventCategory",new EventCategory());
        return "EventCategory/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute EventCategory eventCategory){
        //System.out.println(eventCategory.getName());
        eventCategoryRepository.save(eventCategory);
        return "redirect:";

    }
}
