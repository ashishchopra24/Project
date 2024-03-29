package com.example.demo.name.services.map;

import com.example.demo.name.model.Owner;
import com.example.demo.name.model.Pet;
import com.example.demo.name.services.OwnerService;
import com.example.demo.name.services.PetService;
import com.example.demo.name.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        // TODO Auto-generated method stub
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        // TODO Auto-generated method stub
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        // TODO Auto-generated method stub
        if(object != null){
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);

        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        // TODO Auto-generated method stub
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return null;
    }


}

