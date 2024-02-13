package com.example.authentication;

import com.example.authentication.model.User;
import com.example.authentication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
public class AuthenticationApplication {

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
        };
    }

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);
        }
    }
}
