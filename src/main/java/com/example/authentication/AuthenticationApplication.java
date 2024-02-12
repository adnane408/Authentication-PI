package com.example.authentication;

import com.example.authentication.Repositories.CarteNomRepository;
import com.example.authentication.Repositories.CarteRepository;
import com.example.authentication.Repositories.EmployeRepository;
import com.example.authentication.Repositories.EntrepriseRepository;
import com.example.authentication.model.*;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
public class AuthenticationApplication {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private CarteNomRepository carteRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole("USER");
            userService.saveRole("ADMIN");
            userService.saveUser("user1","1234");
            userService.saveUser("user2","1234");
            userService.addRoletoUser("user1","USER");
            userService.addRoletoUser("user2","ADMIN");
            Employe employe=new Employe("adnane","el hassani","f669214",Employe.ddn("1990-01-15"), Sex.HOMME);
            Entreprise entreprise=new Entreprise("sarl");
            CarteNominative carteNominative=new CarteNominative(entreprise,employe);
            entrepriseRepository.save(entreprise);
            employeRepository.save(employe);
            carteRepository.save(carteNominative);

        };
    }

}
