package com.example.demo.name.repositories;



import com.example.demo.name.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
