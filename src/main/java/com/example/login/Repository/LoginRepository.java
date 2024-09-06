package com.example.login.Repository;

import com.example.login.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserModel,Long> {
    UserModel findByUsernameAndPassword(String username, String password);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);
}
