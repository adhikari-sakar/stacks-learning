package com.example.stacks.learning.securitry.db;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.stacks.learning.securitry.UserRoles.ADMIN;
import static com.example.stacks.learning.securitry.UserRoles.STUDENT;

@Repository("fake")
@RequiredArgsConstructor
public class FakeUserRepository implements ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> loadByName(String username) {
        return applicationUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    private List<ApplicationUser> applicationUsers() {
        return List.of(
                new ApplicationUser("user", passwordEncoder.encode("pass"),
                        true, true, true, true, STUDENT.getGrantedAuthorities()),
                new ApplicationUser("admin", passwordEncoder.encode("pass"),
                        true, true, true, true, ADMIN.getGrantedAuthorities())
        );
    }
}
