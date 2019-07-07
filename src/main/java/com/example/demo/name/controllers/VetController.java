package com.example.demo.name.controllers;

import com.example.demo.name.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetservice;


    public VetController(VetService vetservice)
    {
        this.vetservice=vetservice;
    }



    @RequestMapping({"/vets","/vets/index","/vets/index.html","/vets.html"})
    public String listVets(Model model)
    {
        model.addAttribute("vets", vetservice.findAll());
        return "vet/index";
    }
}
