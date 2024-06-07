package org.example.backend.component;

import lombok.RequiredArgsConstructor;
import org.example.backend.entity.Role;
import org.example.backend.entity.User;
import org.example.backend.entity.enums.RoleName;
import org.example.backend.repo.RoleRepository;
import org.example.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

@Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")){
            Role role1=new Role(1, RoleName.ROLE_ADMIN);
            Role role2=new Role(2, RoleName.ROLE_USER);
            roleRepository.save(role1);
            roleRepository.save(role2);
            User user1=User.builder()
                    .username("Javohir")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(role1))
                    .age(24)
                    .build();
            User user2=User.builder()
                    .username("Nurshod")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(role2 ))
                    .age(18)
                    .build();
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
