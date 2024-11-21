package com.hristo.usermanagement.user.repository;

import com.hristo.usermanagement.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<User> findAllByFirstName(String firstName, Pageable pageable);

    Page<User> findAllByLastName(String lastName, Pageable pageable);

    Page<User> findAllByDateOfBirth(LocalDate dateOfBirth, Pageable pageable);
}
