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
public class InstructorInitializer {

    private final AppProperties appProperties;

    @Bean
    public CommandLineRunner createDefaultInstructor(UserRepository userRepository,
                                                     PasswordEncoder passwordEncoder) {
        return args -> {
            String instructorEmail = appProperties.getDefaultInstructor().getEmail();
            User existingInstructor = userRepository.findByEmail(instructorEmail);

            if (existingInstructor == null) {
                User instructor = new User();
                instructor.setUsername(appProperties.getDefaultInstructor().getUsername());
                instructor.setPassword(passwordEncoder.encode(appProperties.getDefaultInstructor().getPassword()));
                instructor.setEmail(instructorEmail);
                instructor.setRole(UserRole.INSTRUCTOR);
                instructor.setIsActive(true);
                userRepository.save(instructor);
                log.info("Default instructor user created: {}", instructorEmail);
                return;
            }

            boolean updated = false;

            if (!appProperties.getDefaultInstructor().getUsername().equals(existingInstructor.getUsername())) {
                existingInstructor.setUsername(appProperties.getDefaultInstructor().getUsername());
                updated = true;
            }

            if (existingInstructor.getRole() != UserRole.INSTRUCTOR) {
                existingInstructor.setRole(UserRole.INSTRUCTOR);
                updated = true;
            }

            if (existingInstructor.getIsActive() == null || !existingInstructor.getIsActive()) {
                existingInstructor.setIsActive(true);
                updated = true;
            }

            if (!passwordEncoder.matches(appProperties.getDefaultInstructor().getPassword(), existingInstructor.getPassword())) {
                existingInstructor.setPassword(passwordEncoder.encode(appProperties.getDefaultInstructor().getPassword()));
                updated = true;
            }

            if (updated) {
                userRepository.save(existingInstructor);
                log.info("Default instructor user updated for email: {}", instructorEmail);
            } else {
                log.info("Default instructor user already up to date for email: {}", instructorEmail);
            }
        };
    }
}

