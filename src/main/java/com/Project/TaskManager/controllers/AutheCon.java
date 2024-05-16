package com.Project.TaskManager.controllers;
import com.Project.TaskManager.config.UserDelailsSer;
import com.Project.TaskManager.models.User;
import com.Project.TaskManager.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class AutheCon {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserDelailsSer customUserDelailsService;
 
    @Autowired
    public AutheCon(AuthenticationManager authenticationManager,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    UserDelailsSer customUserDelailsService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDelailsService = customUserDelailsService;
    }

    @GetMapping("/TaskScheduler")
    public String start(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getName().equals("anonymousUser")){
            return "redirect:/load";
        }
        return "authentication/start";
    }
    @GetMapping("/sign-up")
    public String getSignup(Model model) {
        return "authentication/sign-up";
    }
    @PostMapping("/sign-up")
    public String signup(@RequestParam String username,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String password,
                         @RequestParam LocalDate dateOfBirth,
                         Model model) {
        if(userRepository.existsByUsername(username)){
            model.addAttribute("exists", true);
            return "authentication/sign-up";
        }
        User user = new User(username, name, surname,
                passwordEncoder.encode(password), dateOfBirth);
        userRepository.save(user);

        return "redirect:/log-in";
    }
    @GetMapping("/log-in")
    public String getLogin(Model model) {
        return "authentication/log-in";
    }
}
