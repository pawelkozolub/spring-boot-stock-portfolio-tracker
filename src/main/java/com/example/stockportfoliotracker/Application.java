package com.example.stockportfoliotracker;

import com.example.stockportfoliotracker.domain.user.Role;
import com.example.stockportfoliotracker.domain.user.User;
import com.example.stockportfoliotracker.repository.RoleRepository;
import com.example.stockportfoliotracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role(null, "ADMIN"));
            Role userRole = roleRepository.save(new Role(null, "USER"));

            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(adminRole);

            User admin = new User(null, "admin", passwordEncoder.encode("pwd"), roleAdmin);
            userRepository.save(admin);

            Set<Role> roleUser = new HashSet<>();
            roleUser.add(userRole);

            User user = new User(null, "user", passwordEncoder.encode("pwd"), roleUser);
            userRepository.save(user);
        };
    }

}
