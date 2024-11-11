package com.hristo.usermanagement.user;

import java.time.LocalDate;

public record UserDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        String email
) {
}
