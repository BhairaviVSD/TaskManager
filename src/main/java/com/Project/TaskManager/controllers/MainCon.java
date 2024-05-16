package com.Project.TaskManager.controllers;

import com.Project.TaskManager.models.Task;
import com.Project.TaskManager.models.User;
import com.Project.TaskManager.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainCon {

    private static User user;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/load")
    public String launch(Model model) {
        user = retreiveUser();
        return "redirect:/home";
    }
    public User retreiveUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow();
    }
    public static User getUser(){
        return user;
    }

}
