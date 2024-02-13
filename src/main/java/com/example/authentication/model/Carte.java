package com.example.authentication.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;


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
    @JsonManagedReference
    @JsonIgnore
    private Entreprise entreprise;

    @OneToMany
    private Collection<Operation> operations;






    public Collection<Operation> getTracabilite() {
            return operations;
    }

    public void addOperation(Operation operation){
        this.operations.add(operation);
    }

    public Carte(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return Float.compare(solde, carte.solde) == 0 && Objects.equals(id, carte.id) && Objects.equals(entreprise, carte.entreprise) && Objects.equals(operations, carte.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solde, entreprise, operations);
    }
}
