package com.example.stacks.learning.securitry.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCredentialRequest {

    private String userName;
    private String password;
}
