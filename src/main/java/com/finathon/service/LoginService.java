package com.finathon.service;

import com.finathon.exceptions.IncorrectPasswordException;
import com.finathon.model.LoginUser;
import com.finathon.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    LoginRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean login(LoginUser user) throws IncorrectPasswordException{
        Optional<LoginUser> repoUser = repository.findById(user.getUserName());
        boolean status=false;
        System.out.println("==========================================================");
        System.out.println("returned user from db is +" + repoUser.get().getUserName());
        System.out.println("returned user from db is +" + repoUser.get().getPassword());
        System.out.println("returned user is +" + user.getUserName());

        if(user.getUserName().equals(repoUser.get().getUserName())){
            if(passwordEncoder.matches(user.getPassword(),repoUser.get().getPassword())){
                System.out.println("login successful");
                return true;
            }
            else {
                throw new IncorrectPasswordException("please enter a valid password");
            }
        }
        return status;
    }
    public boolean saveUser(LoginUser user){
        LoginUser encryptedUser=new LoginUser();
        String encryptedPassword= passwordEncoder.encode(user.getPassword());
        encryptedUser.setUserName(user.getUserName());
        encryptedUser.setPassword(encryptedPassword);
        repository.save(encryptedUser);
        return true;
    }
}
