package com.example.demo.name.controllers;

import com.example.demo.name.model.Owner;
import com.example.demo.name.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerservice;


    public OwnerController(OwnerService ownerservice)
    {
        this.ownerservice=ownerservice;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

/*    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(Model model)
    {

        model.addAttribute("owner",ownerservice.findAll());
        //System.out.println(ownerservice.findById(1L).getLastName());
        return "owner/index";
    }*/

    @RequestMapping("/find")
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }


        List<Owner> results = ownerservice.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owner/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owner/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long id)
    {
     ModelAndView mv=new ModelAndView("owner/ownerDetails");
     mv.addObject(ownerservice.findById(id));
     return mv;
    }

    @GetMapping("/new")
    public String getCreateForm(Model model)
    {
        model.addAttribute("owner",Owner.builder().build());
        return "owner/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String postCreateForm(@Valid Owner owner,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "owner/createOrUpdateOwnerForm";
        else
        {
            Owner object=ownerservice.save(owner);
            return "redirect:/owners/" + object.getId();
        }

    }

    @GetMapping("/{ownerId}/edit")
    public String getUpdateForm(@PathVariable ("ownerId") Long id, Model model)
    {
        model.addAttribute(ownerservice.findById(id));
        return "owner/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String postUpdateForm(@Valid Owner owner,BindingResult bindingResult,@PathVariable ("ownerId") Long id)
    {
        if(bindingResult.hasErrors())
            return "owner/createOrUpdateOwnerForm";
        else
        {
            owner.setId(id);
            Owner object=ownerservice.save(owner);
            return "redirect:/owners/" + object.getId();
        }

    }


}
