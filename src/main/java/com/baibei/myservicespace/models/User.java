package com.baibei.myservicespace.models;

import com.baibei.myservicespace.dto.RegistrationForm;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String paymentInfo;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean enabled = true;

    public User() {
        this.role = "ROLE_USER";
    }

    public User(String username, String password, String email, String paymentInfo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.role = "ROLE_USER";
    }

    public User(String username, String password, String email, String paymentInfo, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.role = role;
    }

    public User(RegistrationForm form, String role) {
        this.username = form.getUsername();
        this.password = form.getPassword();
        this.email = form.getEmail();
        this.role = role;
    }

    public User(RegistrationForm form) {
        this.username = form.getUsername();
        this.password = form.getPassword();
        this.email = form.getEmail();
        this.role = "ROLE_USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}