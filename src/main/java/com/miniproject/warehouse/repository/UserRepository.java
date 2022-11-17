package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);
    User deleteById(int userId);
    User findUserById(int userId);
    User findByUsername(String username);
}
