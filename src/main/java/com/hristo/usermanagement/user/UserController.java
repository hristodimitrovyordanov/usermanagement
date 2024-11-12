package com.hristo.usermanagement.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserResponseDTO saveUser(@RequestBody UserDTO userDTO) {
        return this.userService.saveUser(userDTO);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/sort-by-lastname")
    public List<UserResponseDTO> findAllUsersSortedByLastName() {
        return userService.findAllUsersSortedByLastName();
    }

    @GetMapping("/users/sort-by-date-of-birth")
    public List<UserResponseDTO> findAllUsersSortedByDateOfBirth() {
        return userService.findAllUsersSortedByDateOfBirth();
    }

    @GetMapping("/users/{user-id}")
    public UserResponseDTO findUserById(@PathVariable("user-id") Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/search/{user-name}")
    public List<UserResponseDTO> findUsersByName(@PathVariable("user-name") String name) {
        return userService.findUsersByName(name);
    }

    @DeleteMapping("/users/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("user-id") Integer id) {
        userService.delete(id);
    }
}
