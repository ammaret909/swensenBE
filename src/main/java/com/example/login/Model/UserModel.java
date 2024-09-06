package com.example.login.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user_db")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String gender;
    private String role;
    private boolean checkPrivate;
    private boolean checkNew;
}
