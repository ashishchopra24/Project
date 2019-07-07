package com.example.demo.name.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="vets")
public class Vet extends Person {

    @ManyToMany(fetch=FetchType.EAGER)
            @JoinTable(name="vet_specialities", joinColumns =@JoinColumn(name="vet_id"), inverseJoinColumns = @JoinColumn(name="speciality_id"))
    private Set<Speciality> specialities=new HashSet<>();

}
