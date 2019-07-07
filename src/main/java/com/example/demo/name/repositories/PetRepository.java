package com.example.demo.name.repositories;


import com.example.demo.name.model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface PetRepository extends CrudRepository<Pet,Long> {
}
