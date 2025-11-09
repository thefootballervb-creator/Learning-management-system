package com.lms.dev.config;

import com.lms.dev.entity.User;
import com.lms.dev.enums.UserRole;
import com.lms.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AdminInitializer {

    private final AppProperties appProperties;

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository,
                                                PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = appProperties.getDefaultAdmin().getEmail();
            User existingAdmin = userRepository.findByEmail(adminEmail);

            if (existingAdmin == null) {
                User admin = new User();
                admin.setUsername(appProperties.getDefaultAdmin().getUsername());
                admin.setPassword(passwordEncoder.encode(appProperties.getDefaultAdmin().getPassword()));
                admin.setEmail(adminEmail);
                admin.setRole(UserRole.ADMIN);
                admin.setIsActive(true);
                userRepository.save(admin);
                log.info("Default admin user created with email: {}", adminEmail);
                return;
            }

            boolean updated = false;

            if (!appProperties.getDefaultAdmin().getUsername().equals(existingAdmin.getUsername())) {
                existingAdmin.setUsername(appProperties.getDefaultAdmin().getUsername());
                updated = true;
            }

            if (existingAdmin.getRole() != UserRole.ADMIN) {
                existingAdmin.setRole(UserRole.ADMIN);
                updated = true;
            }

            if (existingAdmin.getIsActive() == null || !existingAdmin.getIsActive()) {
                existingAdmin.setIsActive(true);
                updated = true;
            }

            if (!passwordEncoder.matches(appProperties.getDefaultAdmin().getPassword(), existingAdmin.getPassword())) {
                existingAdmin.setPassword(passwordEncoder.encode(appProperties.getDefaultAdmin().getPassword()));
                updated = true;
            }

            if (updated) {
                userRepository.save(existingAdmin);
                log.info("Default admin user updated for email: {}", adminEmail);
            } else {
                log.info("Default admin user already up to date for email: {}", adminEmail);
            }
        };
    }
}