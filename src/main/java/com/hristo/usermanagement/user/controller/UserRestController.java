package com.hristo.usermanagement.user.controller;

import com.hristo.usermanagement.user.dto.UserDTO;
import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "User API")
@RequestMapping("/user-management/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create user", description = "Return 'userResponseDTO' object, " +
            "which contains only 'firstName', 'lastName' and 'email'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public UserResponseDTO saveUser(@Valid @RequestBody UserDTO userDTO) {
        return this.userService.saveUser(userDTO);
    }

    @Operation(summary = "Get all users with all data", description = "Return a list of 'UserResponseDTO' from all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public List<UserResponseDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Get all users sorted by last name", description = "Return a list of 'UserResponseDTO'" +
            " from all users sorted by last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/sort-by-last-name")
    public List<UserResponseDTO> findAllUsersSortedByLastName() {
        return userService.findAllUsersSortedByLastName();
    }

    @Operation(summary = "Get all users sorted by date of birth", description = "Return a list of 'UserResponseDTO'" +
            " from all users sorted by date of birth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/sort-by-date-of-birth")
    public List<UserResponseDTO> findAllUsersSortedByDateOfBirth() {
        return userService.findAllUsersSortedByDateOfBirth();
    }

    @Operation(summary = "Get user by id", description = "Return a user as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - the user was not found")
    })
    @GetMapping("/{user-id}")
    public UserResponseDTO findUserById(@PathVariable("user-id") Integer id) {


        return userService.findUserById(id);
    }

    @Operation(summary = "Get all users by searched last name", description = "Return a list of 'UserResponseDTO'" +
            " from all users which contains searched last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/search-by-last-name/{user-last-name}")
    public List<UserResponseDTO> findAllUsersByLastName(@PathVariable("user-last-name") String lastName) {
        return userService.findAllUsersByLastName(lastName);
    }

    @Operation(summary = "Get all users by searched first name", description = "Return a list of 'UserResponseDTO'" +
            " from all users which contains searched first name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/search-by-first-name/{user-first-name}")
    public List<UserResponseDTO> findAllUsersByFirstName(@PathVariable("user-first-name") String firstName) {
        return userService.findAllUsersByFirstName(firstName);
    }

    @Operation(summary = "Get user by phone number", description = "Return a 'UserResponseDTO'" +
            " from a searched user which contains the phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/search-by-phone-number/{user-phone-number}")
    public UserResponseDTO findUserByPhoneNumber(@PathVariable("user-phone-number") String phoneNumber) {
        return userService.findUserByPhoneNumber(phoneNumber);
    }

    @Operation(summary = "Get user by email", description = "Return a 'UserResponseDTO'" +
            " from a searched user which contains the email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/search-by-email/{user-email}")
    public UserResponseDTO findUserByEmail(@PathVariable("user-email") String email) {
        return userService.findUserByEmail(email);
    }

    @Operation(summary = "Get all users by searched date of birth", description = "Return a list of 'UserResponseDTO'" +
            " from all users which contains searched date of birth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/search-by-date-of-birth/{user-date-of-birth}")
    public List<UserResponseDTO> findAllUsersByDateOfBirth(@PathVariable("user-date-of-birth") LocalDate dateOfBirth) {
        return userService.findAllUsersByDatOfBirth(dateOfBirth);
    }

    @Operation(summary = "Update user", description = "Return a 'UserResponseDTO' of updated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PutMapping("/{user-id}")
    public UserResponseDTO updateUserById(@Valid @PathVariable("user-id") Integer id, @RequestBody UserDTO userDTO) {
        return userService.updateUserById(id, userDTO);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/{user-id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("user-id") Integer id) {
        userService.delete(id);
    }
}
