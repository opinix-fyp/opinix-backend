package com.opinix.backend.dto;

import com.opinix.backend.model.Role;
import com.opinix.backend.model.User;

public class UserResponse { //class for what returns after registration
    private Long id;
    private String email;
    private String fullName;
    private Role role;
    private boolean active;

    //constructor
    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.active = user.isActive();
    }

    public UserResponse(){} //empty constructor

    //getters
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getFullName(){
        return fullName;
    }
    public Role getRole() {
        return role;
    }
    public boolean isActive() {
        return active;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
