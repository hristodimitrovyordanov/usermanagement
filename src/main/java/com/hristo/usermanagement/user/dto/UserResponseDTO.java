package com.hristo.usermanagement.user.dto;

import java.time.LocalDate;

public record UserResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        String email
) {
}
