package com.example.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entreprise {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "entreprise")
    private List<Carte> cartes;

}
