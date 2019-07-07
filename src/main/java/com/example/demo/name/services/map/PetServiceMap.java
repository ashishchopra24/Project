package com.example.demo.name.services.map;

import com.example.demo.name.model.Pet;
import com.example.demo.name.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        // TODO Auto-generated method stub
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        // TODO Auto-generated method stub
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        // TODO Auto-generated method stub
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {
        // TODO Auto-generated method stub
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        super.deleteById(id);
    }



}

