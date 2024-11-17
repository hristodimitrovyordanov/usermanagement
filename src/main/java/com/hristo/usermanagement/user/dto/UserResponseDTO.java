package com.hristo.usermanagement.user.dto;

public record UserResponseDTO(
        String firstName,
        String lastName,
        String email
) {
}
