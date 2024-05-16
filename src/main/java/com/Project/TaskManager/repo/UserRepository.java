package com.Project.TaskManager.repo;


import org.springframework.data.repository.CrudRepository;

import com.Project.TaskManager.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
