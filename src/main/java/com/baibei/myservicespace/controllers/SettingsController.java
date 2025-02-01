package com.baibei.myservicespace.controllers;

import com.baibei.myservicespace.dto.SettingsForm;
import com.baibei.myservicespace.models.User;
import com.baibei.myservicespace.services.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserDataService userDataService;
    private final PasswordEncoder passwordEncoder;

    public SettingsController(UserDataService userDataService,
                              PasswordEncoder passwordEncoder) {
        this.userDataService = userDataService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String settings(Model model, User user) {
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping
    public String saveSettings(User user, SettingsForm form) {
        if (passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
            if (form.getUsername() != null && !form.getUsername().isEmpty()) {
                user.setUsername(form.getUsername());
            }
            if (form.getEmail() != null && !form.getEmail().isEmpty()) {
                user.setEmail(form.getEmail());
            }
            if (form.getNewPassword() != null && !form.getNewPassword().isEmpty()) {
                if (form.checkPasswords()) {
                    user.setPassword(passwordEncoder.encode(form.getNewPassword()));
                }
            }

            userDataService.save(user);
        }

        return "redirect:/settings";
    }
}
