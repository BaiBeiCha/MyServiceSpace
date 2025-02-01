package com.baibei.myservicespace.controllers.api;

import com.baibei.myservicespace.dto.RegistrationForm;
import com.baibei.myservicespace.models.User;
import com.baibei.myservicespace.services.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserDataService userDataService;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UserDataService userDataService,
                           PasswordEncoder passwordEncoder) {
        this.userDataService = userDataService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getUsers() {
        return userDataService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody RegistrationForm form) {
        User user = new User(form);
        userDataService.save(user);
        return user;
    }

    @PatchMapping
    public User updateProduct(@RequestBody RegistrationForm form) {
        User user = userDataService.findByUsername(form.getUsername());

        if (user != null) {
            if (form.getEmail() != null) {
                user.setEmail(form.getEmail());
            }
            if (form.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(form.getPassword()));
            }
        } else {
            user = new User(form);
        }

        userDataService.save(user);
        return user;
    }

    @DeleteMapping("/{name}")
    public String deleteProduct(@PathVariable String name) {
        User user = userDataService.findByUsername(name);

        if (user != null) {
            userDataService.delete(user);
            return "Successfully deleted user " + name;
        } else {
            return "User " + name + " not found";
        }
    }
}
