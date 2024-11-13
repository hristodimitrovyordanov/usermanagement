package com.hristo.usermanagement.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User API")
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

    @Operation(summary = "Get user by id", description = "Return a user as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - the user was not found")
    })
    @GetMapping("/users/{user-id}")
    public UserResponseDTO findUserById(@PathVariable("user-id") Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/search/{user-name}")
    public List<UserResponseDTO> findUsersByName(@PathVariable("user-name") String name) {
        return userService.findUsersByName(name);
    }

    @PutMapping("/users/{user-id}")
    public UserResponseDTO updateUserById(@PathVariable("user-id") Integer id, @RequestBody UserDTO userDTO) {
        return userService.updateUserById(id, userDTO);
    }

    @DeleteMapping("/users/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("user-id") Integer id) {
        userService.delete(id);
    }
}
