package com.hristo.usermanagement.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "User API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create user", description = "Return 'userResponseDTO' object, " +
            "which contains only 'firstName', 'lastName' and 'email'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/users")
    public UserResponseDTO saveUser(@RequestBody UserDTO userDTO) {
        return this.userService.saveUser(userDTO);
    }

    @Operation(summary = "Get all users", description = "Return a list of 'userResponseDTO' from all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/users")
    public List<UserResponseDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Get all users sorted by last name", description = "Return a list of 'userResponseDTO'" +
            " from all users sorted by last name" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/users/sort-by-lastname")
    public List<UserResponseDTO> findAllUsersSortedByLastName() {
        return userService.findAllUsersSortedByLastName();
    }

    @Operation(summary = "Get all users sorted by date of birth", description = "Return a list of 'userResponseDTO'" +
            " from all users sorted by date of birth" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
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

    @Operation(summary = "Get all users by searched last name", description = "Return a list of 'userResponseDTO'" +
            " from all users which contains searched last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users/search-by-last-name/{user-last-name}")
    public List<UserResponseDTO> findAllUsersByLastName(@PathVariable("user-last-name") String lastName) {
        return userService.findAllUsersByLastName(lastName);
    }

    @Operation(summary = "Get all users by searched first name", description = "Return a list of 'userResponseDTO'" +
            " from all users which contains searched first name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users/search-by-first-name/{user-first-name}")
    public List<UserResponseDTO> findAllUsersByFirstName(@PathVariable("user-first-name") String firstName) {
        return userService.findAllUsersByFirstName(firstName);
    }

    @Operation(summary = "Get user by phone number", description = "Return a 'userResponseDTO'" +
            " from a searched user which contains the phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users/search-by-phone-number/{user-phone-number}")
    public UserResponseDTO findUserByPhoneNumber(@PathVariable("user-phone-number") String phoneNumber) {
        return userService.findUserByPhoneNumber(phoneNumber);
    }

    @Operation(summary = "Get user by email", description = "Return a 'userResponseDTO'" +
            " from a searched user which contains the email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users/search-by-email/{user-email}")
    public UserResponseDTO findUserByEmail(@PathVariable("user-email") String email) {
        return userService.findUsersByEmail(email);
    }

    @Operation(summary = "Get all users by searched date of birth", description = "Return a list of 'userResponseDTO'" +
            " from all users which contains searched date of birth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users/search-by-date-of-birth/{user-date-of-birth}")
    public List<UserResponseDTO> findAllUsersByDateOfBirth(@PathVariable("user-date-of-birth") LocalDate dateOfBirth) {
        return userService.findAllUsersByDatOfBirth(dateOfBirth);
    }

    @Operation(summary = "Update user", description = "Return a 'userResponseDTO' of updated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PutMapping("/users/{user-id}")
    public UserResponseDTO updateUserById(@PathVariable("user-id") Integer id, @RequestBody UserDTO userDTO) {
        return userService.updateUserById(id, userDTO);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/users/{user-id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("user-id") Integer id) {
        userService.delete(id);
    }
}
