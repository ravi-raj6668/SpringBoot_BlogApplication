package io.innodev.blogapp.security.entity;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
