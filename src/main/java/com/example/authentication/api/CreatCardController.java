package com.example.authentication.api;

import com.example.authentication.Repositories.CarteNomRepository;
import com.example.authentication.Repositories.EmployeRepository;
import com.example.authentication.Repositories.EntrepriseRepository;
import com.example.authentication.Requests.CreatCardNominative;
import com.example.authentication.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CreatCardController {
    @Autowired
    private CarteNomRepository carteNomRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    private EmployeDTO mapEmployeToDTO(Employe employe) {
        ModelMapper modelMapper = new ModelMapper();
        EmployeDTO employeDTO = modelMapper.map(employe, EmployeDTO.class);

        // Si vous avez besoin de personnaliser la logique de mapping pour les cartes
        // employeDTO.setCartes(mapCartesToDTO(employe.getCartes()));

        return employeDTO;
    }
    @PostMapping("/creat")
    public ResponseEntity<List<Carte>> addMultipleCards(@RequestBody List<CreatCardNominative> cardNominativeList) {
        List<Carte> carteList = new ArrayList<>();

        for (CreatCardNominative creatCardNominative : cardNominativeList) {
            Employe employe;
            Entreprise entreprise;

            if (entrepriseRepository.findByNom(creatCardNominative.getEntreprise()) == null) {
                System.out.println("null");
                return ResponseEntity.notFound().build();

            } else {
                entreprise = entrepriseRepository.findByNom(creatCardNominative.getEntreprise());
            }

            if (employeRepository.findByIdentite(creatCardNominative.getIdentite()) != null) {
                employe = employeRepository.findByIdentite(creatCardNominative.getIdentite());
            } else {
                employe = new Employe(creatCardNominative.getNom(), creatCardNominative.getTele(), creatCardNominative.getIdentite(), Employe.ddn(creatCardNominative.getDdn()), creatCardNominative.getSex(), entreprise);
                employeRepository.save(employe);
            }
            CarteNominative carteNominative = new CarteNominative(entreprise, employe);
            carteNomRepository.save(carteNominative);
            carteList.add(carteNominative);
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{identite}/cartes")
    public ResponseEntity<EmployeDTO> getCard(@PathVariable String identite) {
        Employe employe = employeRepository.findByIdentite(identite);

        if (employe == null) {
            return ResponseEntity.notFound().build();
        }

        EmployeDTO employeDTO = mapEmployeToDTO(employe);

        return ResponseEntity.ok(employeDTO);
    }

}

