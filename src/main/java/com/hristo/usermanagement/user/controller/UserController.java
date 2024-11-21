package com.hristo.usermanagement.user.controller;

import com.hristo.usermanagement.user.dto.UserDTO;
import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
import com.hristo.usermanagement.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/user-management")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String showForm(Model model) {
        model.addAttribute("user", new User());

        return "index";
    }

    @GetMapping("/users/list-all")
    public String listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<UserResponseDTO> usersPage = userService.getPaginatedUsers(page, size);
        model.addAttribute("usersPage", usersPage);

        return "users";
    }

    @GetMapping("/users/list-all-sorted-by-last-name")
    public String listUsersSortedByLastName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<UserResponseDTO> usersPageSortedByLastName = userService.getPaginatedUsersSortedByLastName(page, size);
        model.addAttribute("usersPageSortedByLastName", usersPageSortedByLastName);

        return "users-sorted-by-last-name";
    }

    @GetMapping("/users/list-all-sorted-by-date-of-birth")
    public String listUsersSortByDateOfBirth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<UserResponseDTO> usersPageSortedByDateOfBirth = userService
                .getPaginatedUsersSortedByDateOfBirth(page, size);
        model.addAttribute("usersPageSortedByDateOfBirth", usersPageSortedByDateOfBirth);

        return "users-sorted-by-date-of-birth";
    }

    @GetMapping("/users/user-by-id")
    public String findUserById(@RequestParam("id") Integer id, Model model) {
        UserResponseDTO userResponseDTO = userService.findUserById(id);
        model.addAttribute("userResponseDTO", userResponseDTO);

        return "user-details";
    }

    @GetMapping("/users/user-form-by-id")
    public String showGetUserFormByID() {
        return "user-form-by-id";
    }

    @GetMapping("/users/user-by-email")
    public String findUserByEmail(@RequestParam("email") String email, Model model) {
        UserResponseDTO userResponseDTO = userService.findUserByEmail(email);
        model.addAttribute("userResponseDTO", userResponseDTO);

        return "user-details";
    }

    @GetMapping("/users/user-form-by-email")
    public String showGetUserFormByEmail() {
        return "user-form-by-email";
    }

    @GetMapping("/users/user-by-phone-number")
    public String findUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber, Model model) {
        UserResponseDTO userResponseDTO = userService.findUserByPhoneNumber(phoneNumber);
        model.addAttribute("userResponseDTO", userResponseDTO);

        return "user-details";
    }

    @GetMapping("/users/user-form-by-phone-number")
    public String showGetUserFormByPhoneNumber() {
        return "user-form-by-phone-number";
    }

    @GetMapping("/users/user-by-specific-first-name")
    public String listUsersBySearchedFirstName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam("firstName") String firstName,
            Model model) {
        Page<UserResponseDTO> usersPageBySpecificFirstName = userService
                .getPaginatedUsersBySpecificFirstName(page, size, firstName);
        model.addAttribute("usersPageBySpecificFirstName", usersPageBySpecificFirstName);
        model.addAttribute("param", Map.of("firstName", firstName));

        return "users-by-specific-first-name";
    }

    @GetMapping("/users/user-form-by-specific-first-name")
    public String showGetUserFormBySearchedFirstName() {
        return "user-form-by-specific-first-name";
    }

    @GetMapping("/users/user-by-specific-last-name")
    public String listUsersBySearchedLastName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam("lastName") String lastName,
            Model model) {
        Page<UserResponseDTO> usersPageBySpecificLastName = userService
                .getPaginatedUsersBySpecificLastName(page, size, lastName);
        model.addAttribute("usersPageBySpecificLastName", usersPageBySpecificLastName);
        model.addAttribute("param", Map.of("lastName", lastName));

        return "users-by-specific-last-name";
    }

    @GetMapping("/users/user-form-by-specific-last-name")
    public String showGetUserFormBySearchedLastName() {
        return "user-form-by-specific-last-name";
    }

    @GetMapping("/users/user-by-date-of-birth")
    public String listUsersBySearchedDateOfBirth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam("dateOfBirth") LocalDate dateOfBirth,
            Model model) {
        Page<UserResponseDTO> usersPageByDateOfBirth = userService
                .getPaginatedUsersByDateOfBirth(page, size, dateOfBirth);
        model.addAttribute("usersPageByDateOfBirth", usersPageByDateOfBirth);
        model.addAttribute("param", Map.of("dateOfBirth", dateOfBirth));

        return "users-by-date-of-birth";
    }

    @GetMapping("/users/user-form-by-date-of-birth")
    public String showGetUserFormByDateOfBirth() {
        return "user-form-by-date-of-birth";
    }

    @PostMapping("users/create")
    public String createUser(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "create-user-form";
        }

        UserResponseDTO savedUser = userService.createUser(userDTO);
        model.addAttribute("userResponseDTO", savedUser);

        return "user-details";
    }

    @GetMapping("/users/create-user")
    public String showPostCreateUser() {
        return "create-user-form";
    }
}
