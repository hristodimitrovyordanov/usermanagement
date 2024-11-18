package com.hristo.usermanagement.user.mapper;

import com.hristo.usermanagement.user.dto.UserDTO;
import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
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

    public UserResponseDTO toUserResponseDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getPhoneNumber(),
                user.getEmail());
    }
}
