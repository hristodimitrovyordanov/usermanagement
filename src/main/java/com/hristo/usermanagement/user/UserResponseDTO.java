package com.hristo.usermanagement.user;

public record UserResponseDTO(
        String firstName,
        String lastName,
        String email
) {
}
