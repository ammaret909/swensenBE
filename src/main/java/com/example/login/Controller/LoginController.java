package com.example.login.Controller;

import com.example.login.Model.request.LoginDTO;
import com.example.login.Model.request.UsermodelDTO;
import com.example.login.Model.response.LoginDTOout;
import com.example.login.Model.response.StatusDTO;
import com.example.login.Model.UserModel;
import com.example.login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginDTOout> CheckLogin(@RequestBody LoginDTO loginDTO){
        return loginService.CheckLogin(loginDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<LoginDTOout> createUser(@RequestBody UsermodelDTO usermodelDTO){
        return loginService.createUser(usermodelDTO);
    }

}
