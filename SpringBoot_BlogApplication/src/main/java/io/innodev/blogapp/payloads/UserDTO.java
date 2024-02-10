package io.innodev.blogapp.payloads;

import io.innodev.blogapp.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "User name must be min of 4 chars")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be min of 4 chars and max of 10 chars")
//    @Pattern(regexp = "A-Z,a-z")
    private String password;
    @NotEmpty
    private String about;

    private Set<Role> roles = new HashSet<>();
}
