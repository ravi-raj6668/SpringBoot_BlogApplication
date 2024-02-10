package io.innodev.blogapp.entity;

import io.innodev.blogapp.config.ValidValues;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @ValidValues
    private int id;
    private String roleName;
}
