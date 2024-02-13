package com.example.authentication.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DiscriminatorValue("NON")

public class CarteNonNominative extends Carte{

}
