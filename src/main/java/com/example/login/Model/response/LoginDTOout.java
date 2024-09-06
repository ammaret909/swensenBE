package com.example.login.Model.response;

import lombok.Data;

@Data
public class LoginDTOout {
    private String status;
    private String description;
    private String token;
    private String username;
}
