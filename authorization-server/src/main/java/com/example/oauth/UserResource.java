package com.example.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserResource {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("{/userId}")
    ResponseEntity<AppUser> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping
    ResponseEntity<List<AppUser>> users() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/save/user")
    ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        return response(userService::saveUser, user);
    }

    @PostMapping("/save/role")
    ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return response(roleRepository::save, role);
    }

    @PostMapping("/addRole/{userId}/{roleName}")
    ResponseEntity<AppUser> addRoleToUser(@PathVariable String userId, @PathVariable String roleName) {
        return new ResponseEntity<>(userService.addRoleToUser(userId, roleName), HttpStatus.CREATED);
    }

    private <T> ResponseEntity<T> response(Function<T, T> function, @Nullable T body) {
        return new ResponseEntity<>(function.apply(body), HttpStatus.CREATED);
    }
}
