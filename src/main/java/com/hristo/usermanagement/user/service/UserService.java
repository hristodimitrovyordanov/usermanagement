package com.hristo.usermanagement.user.service;

import com.hristo.usermanagement.user.dto.UserDTO;
import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
import com.hristo.usermanagement.user.exception.UserNotFoundException;
import com.hristo.usermanagement.user.mapper.UserMapper;
import com.hristo.usermanagement.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO saveUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDto(savedUser);
    }

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> findAllUsersSortedByLastName() {
        return userRepository.findAll()
                .stream()
                .sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()))
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> findAllUsersSortedByDateOfBirth() {
        return userRepository.findAll()
                .stream()
                .sorted((o1, o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()))
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findUserById(Integer id) {
        Integer maxId = userRepository.findMaxId();

        if (maxId == null) {
            throw new UserNotFoundException("The user is not found in the database.");
        }

        if (id > maxId || id < 1) {
            throw new UserNotFoundException("User id not found: " + id);
        }

        return userRepository.findById(id)
                .map(userMapper::toUserResponseDto)
                .orElse(null);
    }

    public List<UserResponseDTO> findAllUsersByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName)
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> findAllUsersByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName)
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());

    }

    public UserResponseDTO findUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber);
        return userMapper.toUserResponseDto(user);
    }

    public UserResponseDTO findUsersByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userMapper.toUserResponseDto(user);
    }

    public List<UserResponseDTO> findAllUsersByDatOfBirth(LocalDate dateOfBirth) {
        return userRepository.findAllByDateOfBirth(dateOfBirth)
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO updateUserById(Integer id, UserDTO userDTO) {
        User updatedUser = userMapper.toUser(userDTO);
        updatedUser.setId(id);
        userRepository.save(updatedUser);

        return userMapper.toUserResponseDto(updatedUser);
    }
}
