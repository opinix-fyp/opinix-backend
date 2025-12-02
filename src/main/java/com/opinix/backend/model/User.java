package com.opinix.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") //KEEP THIS AS USERS BECAUSE USER IS A SQL KEYWORD THIS WILL BREAK THE DB
public class User {
    //initialization to link to database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; //TODO: Hash this for extra protection in the future

    @Column(nullable = false)
    private String fullName;

    private boolean active = true;
    //more fields can be added in later.. but rn idk what else to add

    //constructor
    public User(){}

    //setters
    public void setId(long id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    //getters
    public long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getFullName(){
        return fullName;
    }
    public boolean isActive(){
        return active;
    }


}
