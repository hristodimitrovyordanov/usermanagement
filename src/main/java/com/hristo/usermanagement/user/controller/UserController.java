package com.hristo.usermanagement.user.controller;

import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
import com.hristo.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        Page<UserResponseDTO> usersPageSortedByDateOfBirth = userService.getPaginatedUsersSortedByDateOfBirth(page, size);
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
}
