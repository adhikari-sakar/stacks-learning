package com.example.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;

    public AppUser findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId));
    }

    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public AppUser addRoleToUser(String userId, String roleName) {
        return saveUser(userRepository
                .findByUserId(userId)
                .map(user -> addRole(roleName, user))
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));
    }

    private AppUser addRole(String roleName, AppUser user) {
        var userRole = findRoleOrThrow(roleName);
        user.add(userRole);
        return user;
    }

    private Role findRoleOrThrow(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found " + roleName));
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }
}
