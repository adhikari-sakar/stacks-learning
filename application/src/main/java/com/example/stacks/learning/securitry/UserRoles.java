package com.example.stacks.learning.securitry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.stacks.learning.securitry.UserPermission.*;

@Getter
@RequiredArgsConstructor
public enum UserRoles {

    STUDENT(Set.of(STUDENT_READ, COURSE_READ)),
    ADMIN(Set.of(STUDENT_READ, COURSE_READ, STUDENT_WRITE, COURSE_WRITE));

    private final Set<UserPermission> permissions;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
