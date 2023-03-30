package com.chandansingh16794.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;

    @NotEmpty(message = "First name is not empty")
    String firstName;

    @NotEmpty(message = "Last name is not empty")
    String lastName;

    @NotEmpty(message = "Email is not empty")
    @Email(message = "Email is not valid")
    String email;

    @NotEmpty(message = "Phone is not empty")
    String phone;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
