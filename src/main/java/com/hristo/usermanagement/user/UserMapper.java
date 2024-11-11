package com.hristo.usermanagement.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(UserDTO userDTO) {

        if (userDTO == null) {
            throw new NullPointerException("The user DTO should not be null.");
        }

        User user = new User();
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setDateOfBirth(userDTO.dateOfBirth());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setEmail(userDTO.email());

        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
