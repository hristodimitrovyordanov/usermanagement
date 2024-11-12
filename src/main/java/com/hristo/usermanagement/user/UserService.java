package com.hristo.usermanagement.user;

import org.springframework.stereotype.Service;

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

        return userMapper.toUserResponseDTO(savedUser);
    }

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> findAllUsersSortedByLastName() {
        return userRepository.findAll()
                .stream()
                .sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()))
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> findAllUsersSortedByDateOfBirth() {
        return userRepository.findAll()
                .stream()
                .sorted((o1, o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()))
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponseDTO)
                .orElse(null);
    }

    public List<UserResponseDTO> findUsersByName(String name) {
        return userRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
