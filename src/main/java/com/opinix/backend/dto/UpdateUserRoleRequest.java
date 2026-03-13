package com.opinix.backend.dto;

import com.opinix.backend.model.Role;

public class UpdateUserRoleRequest {
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
