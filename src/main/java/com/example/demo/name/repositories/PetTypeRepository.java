package com.example.demo.name.repositories;


import com.example.demo.name.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
