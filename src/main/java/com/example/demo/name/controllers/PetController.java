package com.example.demo.name.controllers;

import com.example.demo.name.model.Owner;
import com.example.demo.name.model.Pet;
import com.example.demo.name.model.PetType;
import com.example.demo.name.services.OwnerService;
import com.example.demo.name.services.PetService;
import com.example.demo.name.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import javax.validation.Valid;


@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;
    private  final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }


    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @GetMapping("/pets/new")
    public String getCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pet/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String postCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return "pet/createOrUpdatePetForm";
        } else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String getUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return  "pet/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String postUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return  "pet/createOrUpdatePetForm";
        } else {
            //owner.getPets().add(pet);
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
