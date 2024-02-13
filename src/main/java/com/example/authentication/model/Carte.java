package com.example.authentication.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
public abstract class Carte {
    @Id
    @GeneratedValue
    private Long id;
    private float solde;
    @ManyToOne
    private Entreprise entreprise;

    @OneToMany
    private Collection<Operation> operations;

    public Collection<Operation> getTracabilite() {
            return operations;
    }

}
