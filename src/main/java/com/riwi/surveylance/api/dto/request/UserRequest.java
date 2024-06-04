package com.riwi.surveylance.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    
    private int id;

    @NotBlank(message = "The name is required")
    @Size(min = 4, max = 20, message = "The minimum username length is 4, max is 15")
    private String name;

    @Email(message = "Please insert a valid email")
    @NotBlank(message = "The email is required")
    private String email;

    @NotBlank(message = "The password is required")
    @Size(min = 4, max = 20, message = "The minimum password length is 4, max is 20")
    private String password;

    @NotNull(message = "The status is required")
    private boolean active;

}
