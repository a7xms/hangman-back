package com.example.hangman.models;

import com.example.hangman.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", indexes = {@Index(name = "uk_users_email_index", columnList = "email", unique = true)})
@Getter
@Setter
public class User extends BaseModel {

    private String email;

    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_confirmed")
    private Boolean emailConfirmed = false;


}
