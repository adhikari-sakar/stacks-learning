package com.example.stacks.learning.securitry.db;

import java.util.Optional;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> loadByName(String username);
}
