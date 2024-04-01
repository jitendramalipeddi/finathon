package com.finathon.controller;

import com.finathon.exceptions.IncorrectPasswordException;
import com.finathon.exceptions.UserAlreadyPresentException;
import com.finathon.model.LoginUser;
import com.finathon.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public void loginUser(@RequestBody LoginUser user){
        try{
            loginService.login(user);
        }
        catch (IncorrectPasswordException exception){
            System.out.println(exception.getMessage());
        }
    }
    @PostMapping("/save")
    public void saveUser(@RequestBody LoginUser user){
        try {
            loginService.saveUser(user);
        } catch (UserAlreadyPresentException e) {
            System.out.println(e.getMessage());
        }
    }
}
