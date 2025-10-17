package com.devansh.apiobserver.controller;

import com.devansh.apiobserver.dto.LoginRequest;
import com.devansh.apiobserver.dto.ServiceDTO;
import com.devansh.apiobserver.dto.UserDTO;
import com.devansh.apiobserver.model.User;
import com.devansh.apiobserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticate(@RequestBody LoginRequest user) {
        UserDTO authenticated = userService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(authenticated);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO service, @PathVariable Long userId) {
        userService.createOrAddService(service, userId);
        return ResponseEntity.ok(service);
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(userService.getAllServices());
    }
}
