package com.hristo.usermanagement.user.repository;

import com.hristo.usermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByLastName(String latName);

    List<User> findAllByFirstName(String firstName);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByEmail(String email);

    List<User> findAllByDateOfBirth(LocalDate dateOfBirth);
}
