package com.cloudmusic.controller;

import com.cloudmusic.service.CodeService;
import com.cloudmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private CodeService codeService;
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info(){
        return "detail/info";
    }

    private String getUsername(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @GetMapping("/resetPwd")
    public String resetPwd(){
        String username = getUsername();
        codeService.sendCodeMail(username);
        return "detail/resetPwd";
    }

    @PostMapping("/resetPwd")
    public String resetPwd(@RequestParam(value = "code") String code,
                           @RequestParam(value = "password") String password,
                           Model model){
        String msg = "验证码错误或超时，重置密码失败";
        String username = getUsername();
        if (codeService.checkCode(username, code)){
            userService.resetPwd(username, password);
            msg = "重置成功";
        }
        model.addAttribute("msg", msg);
        return "detail/resetPwdResult";
    }
}
