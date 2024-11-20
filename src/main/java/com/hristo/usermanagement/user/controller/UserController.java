package com.hristo.usermanagement.user.controller;

import com.hristo.usermanagement.user.dto.UserResponseDTO;
import com.hristo.usermanagement.user.entity.User;
import com.hristo.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        UserResponseDTO userResponseDTO = userService.findUserById(id);
        model.addAttribute("userResponseDTO", userResponseDTO);

        return "user-by-id";
    }
}
