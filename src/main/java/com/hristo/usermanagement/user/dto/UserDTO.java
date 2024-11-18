package com.hristo.usermanagement.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserDTO(
        @NotNull(message = "First name is required")
        @Size(min = 1, message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        @Size(min = 1, message = "Last name is required")
        String lastName,
        @NotNull(message = "Date of birth is required")
        @PastOrPresent(message = "The date of birth must not be in future")
        LocalDate dateOfBirth,
        @NotNull(message = "Phone number is required")
        @Size(min = 8, message = "Phone number is required")
        String phoneNumber,
        @Email(message = "Please provide a valid email address")
        @NotNull(message = "Email is required")
        String email
) {
}
