package io.innodev.blogapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blog_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
