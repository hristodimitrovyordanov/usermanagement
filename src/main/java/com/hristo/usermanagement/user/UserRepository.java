package com.hristo.usermanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByLastName(String latName);

    List<User> findAllByFirstName(String firstName);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByEmail(String email);

    List<User> findAllByDateOfBirth(LocalDate dateOfBirth);
}
