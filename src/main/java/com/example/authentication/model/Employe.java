package com.example.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employe {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "employe")
    private Collection<CarteNominative> cartesNominatives;


}
