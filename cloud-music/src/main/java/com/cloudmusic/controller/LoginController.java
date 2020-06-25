package com.cloudmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/userLogin")
    public String userLogin(){
        return "login/login";
    }

}
