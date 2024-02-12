package com.example.authentication.Repositories;

import com.example.authentication.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
    public Entreprise findByNom(String nom);
}
