package org.launchCode.codingevents.controllers;

import org.launchCode.codingevents.data.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.launchCode.codingevents.models.Tag;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String DisplayAllTags(Model model){
        model.addAttribute("title" , "All Tags");
        model.addAttribute("tags",tagRepository.findAll());
        return "tag/index";
    }

    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("title","Create Tag");
        model.addAttribute("tag", new Tag());
        return "tag/create";
    }

    @PostMapping("create")
    public String create(@ModelAttribute Tag newTag, Model model){
        //newTag.setName("#"+newTag.getName());
        tagRepository.save(newTag);
        return "redirect:";

    }
}
