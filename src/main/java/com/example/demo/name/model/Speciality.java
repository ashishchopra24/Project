package com.example.demo.name.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="specialities")
public class Speciality extends BaseEntity{

    @Column(name="description")
    String description;

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/
}
