package com.example.login.Model.request;

import lombok.Data;

import java.util.Date;
@Data
public class UsermodelDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String gender;
    private boolean checkPrivate;
    private boolean checkNew;
}
