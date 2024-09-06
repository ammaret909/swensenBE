package com.example.login.Service;

import com.example.login.Model.request.LoginDTO;
import com.example.login.Model.request.UsermodelDTO;
import com.example.login.Model.response.LoginDTOout;
import com.example.login.Model.response.StatusDTO;
import com.example.login.Model.UserModel;
import com.example.login.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    JwtService jwtService;

    public ResponseEntity<LoginDTOout> CheckLogin(LoginDTO loginDTO){
        try {
            UserModel userModelCheck = loginRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
            LoginDTOout loginDTOout = new LoginDTOout();
            if (userModelCheck == null) {
                loginDTOout.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED));
                loginDTOout.setDescription("unauthorized");
                return new ResponseEntity<>(loginDTOout,HttpStatus.UNAUTHORIZED);
            }else {
                loginDTOout.setStatus(String.valueOf(HttpStatus.OK));
                String jwtToken = jwtService.generateAccessToken(userModelCheck.getUsername());
                loginDTOout.setDescription("success");
                loginDTOout.setToken(jwtToken);
                loginDTOout.setUsername(userModelCheck.getUsername());
                return new ResponseEntity<>(loginDTOout,HttpStatus.OK);
            }

        }catch (Exception e) {
            LoginDTOout loginDTOout = new LoginDTOout();
            loginDTOout.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            loginDTOout.setDescription(e.getMessage());
            return new ResponseEntity<>(loginDTOout, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<LoginDTOout> createUser(UsermodelDTO usermodelDTO) {
        try {
            if (loginRepository.existsByPhoneNumber(usermodelDTO.getPhoneNumber())) {
                LoginDTOout loginDTOout = new LoginDTOout();
                loginDTOout.setStatus(String.valueOf(HttpStatus.CONFLICT));
                loginDTOout.setDescription("Phone number already exists.");
                return new ResponseEntity<>(loginDTOout, HttpStatus.CONFLICT);
            }
            if (loginRepository.existsByUsername(usermodelDTO.getUsername())) {
                LoginDTOout loginDTOout = new LoginDTOout();
                loginDTOout.setStatus(String.valueOf(HttpStatus.CONFLICT));
                loginDTOout.setDescription("Email number already exists.");
                return new ResponseEntity<>(loginDTOout, HttpStatus.CONFLICT);
            }

            UserModel userModel = new UserModel();
            userModel.setUsername(usermodelDTO.getUsername());
            userModel.setPassword(usermodelDTO.getPassword());
            userModel.setFirstName(usermodelDTO.getFirstName());
            userModel.setLastName(usermodelDTO.getLastName());
            userModel.setBirthday(usermodelDTO.getBirthday());
            userModel.setPhoneNumber(usermodelDTO.getPhoneNumber());
            userModel.setGender(usermodelDTO.getGender());
            userModel.setCheckPrivate(usermodelDTO.isCheckPrivate());
            userModel.setCheckNew(usermodelDTO.isCheckNew());
            userModel.setRole("user");

            loginRepository.save(userModel);
            LoginDTOout statusDTO = new LoginDTOout();
            statusDTO.setStatus(String.valueOf(HttpStatus.OK));
            String jwtToken = jwtService.generateAccessToken(userModel.getUsername());
            statusDTO.setDescription("success");
            statusDTO.setToken(jwtToken);
            statusDTO.setUsername(userModel.getUsername());
            return new ResponseEntity<>(statusDTO, HttpStatus.OK);
        } catch (Exception e){
            LoginDTOout loginDTOout = new LoginDTOout();
            loginDTOout.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            loginDTOout.setDescription(e.getMessage());
            return new ResponseEntity<>(loginDTOout, HttpStatus.BAD_REQUEST);
        }
    }

}
