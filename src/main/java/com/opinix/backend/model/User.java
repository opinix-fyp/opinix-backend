package com.opinix.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") //KEEP THIS AS USERS BECAUSE USER IS A SQL KEYWORD THIS WILL BREAK THE DB
public class User {
    //initialization to link to database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; //TODO: Hash this for extra protection in the future



    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.RESPONDENT; //default role is USER
    //NOTE: this role field is never used. there is no setter or getter for it. currently just here in case. when needed, will be used.

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active = true;
    //more fields can be added in later.. but rn idk what else to add

    //constructor
    public User(){}

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    //getters
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public String getFullName(){
        return fullName;
    }
    public boolean isActive(){
        return active;
    }

    public Role getRole(){
        return role;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
}
