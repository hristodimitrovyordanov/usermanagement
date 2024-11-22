package com.hristo.usermanagement.user.service;

import com.hristo.usermanagement.user.dto.UserDTO;
import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
import com.hristo.usermanagement.user.exception.UserNotFoundException;
import com.hristo.usermanagement.user.mapper.UserMapper;
import com.hristo.usermanagement.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final String USER_NOT_FOUND = "User not found with id: ";
    private static final String LAST_NAME = "lastName";
    private static final String DATE_OF_BIRTH = "dateOfBirth";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

        if (doesIDNotExist(id)) {
            throw new UserNotFoundException(USER_NOT_FOUND + id);
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

    public UserResponseDTO findUserByEmail(String email) {
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

        if (doesIDNotExist(id)) {
            throw new UserNotFoundException(USER_NOT_FOUND + id);
        }

        userRepository.deleteById(id);
    }

    public UserResponseDTO updateUserById(Integer id, UserDTO userDTO) {

        if (doesIDNotExist(id)) {
            throw new UserNotFoundException(USER_NOT_FOUND + id);
        }

        User updatedUser = userMapper.toUser(userDTO);
        updatedUser.setId(id);
        userRepository.save(updatedUser);

        return userMapper.toUserResponseDto(updatedUser);
    }

    public Page<UserResponseDTO> getPaginatedUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable).map(userMapper::toUserResponseDto);
    }

    public Page<UserResponseDTO> getPaginatedUsersSortedByLastName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(LAST_NAME).ascending());

        return userRepository.findAll(pageable).map(userMapper::toUserResponseDto);
    }

    public Page<UserResponseDTO> getPaginatedUsersSortedByDateOfBirth(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(DATE_OF_BIRTH).ascending());

        return userRepository.findAll(pageable).map(userMapper::toUserResponseDto);
    }

    public Page<UserResponseDTO> getPaginatedUsersBySpecificFirstName(int page, int size, String firstName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAllByFirstName(firstName, pageable);

        return usersPage.map(userMapper::toUserResponseDto);
    }

    public Page<UserResponseDTO> getPaginatedUsersBySpecificLastName(int page, int size, String lastName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAllByLastName(lastName, pageable);

        return usersPage.map(userMapper::toUserResponseDto);
    }

    public Page<UserResponseDTO> getPaginatedUsersByDateOfBirth(int page, int size, LocalDate dateOfBirth) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAllByDateOfBirth(dateOfBirth, pageable);

        return usersPage.map(userMapper::toUserResponseDto);
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDto(savedUser);
    }

    private boolean doesIDNotExist(Integer id) {
        return !userRepository.existsById(id);
    }
}
