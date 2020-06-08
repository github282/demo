package com.cloudmusic.controller;

import com.cloudmusic.domain.User;
import com.cloudmusic.domian.User;
import com.cloudmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/userLogin")
    public String userLogin(){
        return "login/login";
    }

    @GetMapping("/register")
    public String register(){
        return "login/register";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.register(user);
        return "index";
    }
}
