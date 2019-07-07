package com.example.demo.name.bootstrap;

import com.example.demo.name.model.*;
import com.example.demo.name.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private  final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService, VisitService visitService)
    {
        this.ownerService=ownerService;
        this.vetService=vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        int count=petTypeService.findAll().size();
        if(count==0)
        loadData();


    }

    private void loadData() {
        PetType dog=new PetType();
        dog.setName("dog");
        PetType dogpettype=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("cat");
        PetType catpettype=petTypeService.save(cat);

        System.out.println("loaded pettype data");

        Speciality radiology=new Speciality();
        radiology.setDescription("radiology");
        Speciality  saveradiology=specialitiesService.save(radiology);

        Speciality  surgery=new Speciality();
        surgery.setDescription("surgery");
        Speciality  savesurgeryy=specialitiesService.save(surgery);

        Speciality  dentistry=new Speciality();
        dentistry.setDescription("dentistry");
        Speciality  savedentistry=specialitiesService.save(dentistry);

        Owner owner1=new Owner();
        owner1.setFirstName("ally");
        owner1.setLastName("nilson");
        owner1.setAddress("123 street lane");
        owner1.setCity("florida");
        owner1.setTelephone("01872323");

        Pet pet1=new Pet();
        pet1.setPetType(dogpettype);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setName("lebradog");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("paam");
        owner2.setLastName("jake");
        owner2.setAddress("123 street lane");
        owner2.setCity("florida");
        owner2.setTelephone("01872323");

        Pet pet2=new Pet();
        pet2.setPetType(catpettype);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());
        pet2.setName("mini");
        owner2.getPets().add(pet2);




        Pet pet3=new Pet();
        pet3.setPetType(catpettype);
        pet3.setOwner(owner2);
        pet3.setBirthDate(LocalDate.now());
        pet3.setName("meow");
        owner2.getPets().add(pet3);

        ownerService.save(owner2);

        Visit dogVisit=new Visit();
        dogVisit.setPet(pet1);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("dog is visiting");

        visitService.save(dogVisit);

        System.out.println("Owner data is loaded");

        Vet vet1=new Vet();
        vet1.setFirstName("black");
        vet1.setLastName("john");
        vet1.getSpecialities().add(saveradiology);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("cruze");
        vet2.setLastName("blade");
        vet2.getSpecialities().add(savesurgeryy);

        vetService.save(vet2);

        System.out.println("Vet data is loaded");
    }

}
