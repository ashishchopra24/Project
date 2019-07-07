package com.example.demo.name.services.map;

import com.example.demo.name.model.Speciality;
import com.example.demo.name.model.Vet;
import com.example.demo.name.services.SpecialitiesService;
import com.example.demo.name.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialitiesService specialitiesService;

    public VetServiceMap(SpecialitiesService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Set<Vet> findAll() {
        // TODO Auto-generated method stub
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        // TODO Auto-generated method stub
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        // TODO Auto-generated method stub
        if (object.getSpecialities().size() > 0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpecialty = specialitiesService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        // TODO Auto-generated method stub
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        super.deleteById(id);
    }



}

