package com.example.authentication.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("NOMI")

public class CarteNominative extends Carte{
    @ManyToOne
    private Employe employe;

    public CarteNominative(Entreprise entreprise, Employe employe) {
        super(entreprise);
        this.employe = employe;
    }
}
