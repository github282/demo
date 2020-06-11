package com.cloudmusic.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/info")
    public String info(){
        return "user/info";
    }

}
