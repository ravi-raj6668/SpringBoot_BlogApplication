package io.innodev.blogapp.payloads;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RoleDTO {
    @Id
    private int id;
    private String roleName;
}
