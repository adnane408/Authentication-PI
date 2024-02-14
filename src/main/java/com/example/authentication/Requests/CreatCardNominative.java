package com.example.authentication.Requests;

import com.example.authentication.model.Entreprise;
import com.example.authentication.model.Sex;
import lombok.Data;

@Data
public class CreatCardNominative {
    private String identite;
    private boolean isNominative;
    private String nom;
    private String ddn;
    private String tele;
    private Sex sex;
    private String entreprise;
}
