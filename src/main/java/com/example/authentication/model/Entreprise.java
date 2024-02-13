package com.example.authentication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entreprise {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nom;
    @OneToMany(mappedBy = "entreprise")
    @JsonBackReference
    private List<Carte> cartes=new ArrayList<>();
    @OneToMany(mappedBy = "entreprise", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Employe> employes=new ArrayList<>();

    public Entreprise(String nom){
        this.nom=nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }


    public void addEmploye(Employe employe){
        this.employes.add(employe);
    }
    public void addCard(Carte carte){
        this.cartes.add(carte);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entreprise that = (Entreprise) o;
        return Objects.equals(id, that.id) && Objects.equals(nom, that.nom) && Objects.equals(cartes, that.cartes) && Objects.equals(employes, that.employes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, cartes, employes);
    }
}
