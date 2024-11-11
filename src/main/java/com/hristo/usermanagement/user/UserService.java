package com.hristo.usermanagement.user;

import org.springframework.stereotype.Service;

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
}
