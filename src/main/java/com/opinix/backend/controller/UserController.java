package com.opinix.backend.controller;

import com.opinix.backend.dto.UpdateUserRoleRequest;
import com.opinix.backend.dto.UpdateUserStatusRequest;
import com.opinix.backend.dto.UserResponse;
import com.opinix.backend.service.AuthService;
import com.opinix.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}/role")
    public UserResponse updateUserRole(@PathVariable Long id,
        @RequestBody UpdateUserRoleRequest request) {
        return userService.updateUserRole(id, request.getRole());
    }

    @PatchMapping("/{id}/active")
    public UserResponse updateUserActiveStatus(@PathVariable Long id,
        @RequestBody UpdateUserStatusRequest request) {
        return userService.updateUserActiveStatus(id, request.isActive());
    }

    //me endpoint
        @GetMapping("/me")
    public UserResponse getCurrentUser(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
