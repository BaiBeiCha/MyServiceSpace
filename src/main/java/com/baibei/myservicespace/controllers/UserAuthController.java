package com.baibei.myservicespace.controllers;

import com.baibei.myservicespace.dto.RegistrationForm;
import com.baibei.myservicespace.models.User;
import com.baibei.myservicespace.repositories.UserRepository;
import com.baibei.myservicespace.services.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserAuthController(UserDataService userDataService,
                              PasswordEncoder passwordEncoder,
                              UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        //TO DELETE
        userDataService.initAdmin();
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String errorParam) {
        if (errorParam != null && !errorParam.isEmpty()) {
            model.addAttribute("status", "Username or password is incorrect!");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form, Model model) {
        if (userRepository.existsByUsername(form.getUsername())) {
            model.addAttribute("status", "Username is already in use!");
            return "redirect:/register";
        }

        if (form.checkPasswords()) {
            if (form.getPassword().length() < 8) {
                model.addAttribute("status", "Password must be at least 8 characters!");
                return "redirect:/register";
            }

            form.setPassword(passwordEncoder.encode(form.getPassword()));
            User newUser = new User(form);
            userRepository.save(newUser);
            return "redirect:/shop";
        } else {
            model.addAttribute("message", "Username or password is incorrect!");
            return "redirect:/register";
        }
    }
}
