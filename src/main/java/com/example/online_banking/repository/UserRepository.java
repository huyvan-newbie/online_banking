package com.example.online_banking.repository;

import com.example.online_banking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPhoneNumber(String phoneNumber);
    User findByUsername(String userName);

}
