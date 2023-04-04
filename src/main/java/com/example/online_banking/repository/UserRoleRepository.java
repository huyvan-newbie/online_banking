package com.example.online_banking.repository;

import com.example.online_banking.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select a.roleName from UserRole a where a.userId = :userId")
    List<String> findRoleNameByUser(@Param("userId") Long userId);
}
