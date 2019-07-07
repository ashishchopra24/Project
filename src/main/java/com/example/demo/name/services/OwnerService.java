package com.example.demo.name.services;

import com.example.demo.name.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastname);

    List<Owner> findAllByLastNameLike(String lastName);


}
