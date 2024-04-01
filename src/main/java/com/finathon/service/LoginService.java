package com.finathon.service;

import com.finathon.exceptions.IncorrectPasswordException;
import com.finathon.exceptions.UserAlreadyPresentException;
import com.finathon.model.LoginUser;
import com.finathon.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(LoginService.class);


    public boolean login(LoginUser user) throws IncorrectPasswordException{
        return checkUser(user);
    }
    public boolean saveUser(LoginUser user) throws UserAlreadyPresentException {
        if(!checkUserExist(user)){
            LoginUser encryptedUser=new LoginUser();
            String encryptedPassword= passwordEncoder.encode(user.getPassword());
            encryptedUser.setUserName(user.getUserName());
            encryptedUser.setPassword(encryptedPassword);
            repository.save(encryptedUser);
        }
        else{
            throw new UserAlreadyPresentException("entered user already present");
        }
        return true;
    }

    public boolean checkUser(LoginUser user) throws IncorrectPasswordException {
        boolean status=false;
        Optional<LoginUser> repoUser = repository.findById(user.getUserName());
        if (user.getUserName().equals(repoUser.get().getUserName())) {
            if (passwordEncoder.matches(user.getPassword(), repoUser.get().getPassword())) {
                logger.info("login succesfful by " + user.getUserName());
                return true;
            } else {
                throw new IncorrectPasswordException("please enter a valid password");
            }
        }
        return status;
    }

    public boolean checkUserExist(LoginUser user){
        if(repository.existsByUserName(user.getUserName())){
            return true;
        }
        return false;
    }
}
