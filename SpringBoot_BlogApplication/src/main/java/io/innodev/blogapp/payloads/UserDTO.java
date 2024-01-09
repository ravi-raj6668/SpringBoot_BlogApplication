package io.innodev.blogapp.payloads;

import jakarta.validation.constraints.*;
import lombok.*;

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
}
