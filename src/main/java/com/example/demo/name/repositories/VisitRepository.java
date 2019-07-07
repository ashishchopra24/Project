package com.example.demo.name.repositories;



import com.example.demo.name.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
