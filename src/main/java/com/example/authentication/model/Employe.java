package com.example.authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
    private String tele;
    @Column(unique = true)
    private String identite;
    private Date ddn;
    private Sex sex;
    @OneToMany(mappedBy = "employe")
    private Collection<CarteNominative> cartesNominatives;

    public Employe(String nom, String prenom, String identite, Date ddn, Sex sex) {
        this.nom = nom;
        this.prenom = prenom;
        this.identite = identite;
        this.ddn = ddn;
        this.sex = sex;
    }

    public Employe(String nom, String identite, Date ddn, Sex sex) {
        this.nom = nom;
        this.identite = identite;
        this.ddn = ddn;
        this.sex = sex;
    }

    public static Date ddn(String ddnString){
        Date ddn = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ddn = dateFormat.parse(ddnString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ddn;
    }
}
