package com.cloudmusic.controller.user;

import com.cloudmusic.domian.User;
import com.cloudmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info(){
        return "user/info";
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

    @GetMapping("/resetPwd")
    public String resetPwd(){
        return "user/resetPwd";
    }

    /**
     * 处理ajax请求
     * 发送重置密码邮件
     * @return msg
     */
    @ResponseBody
    @GetMapping("/sendCodeMail")
    public String sendCodeMail(){
        String msg = userService.sendCodeMail();
        return msg;
    }

    @PostMapping("/resetPwd")
    public String resetPwd(@RequestParam(value = "code") String code,
                           @RequestParam(value = "password") String password,
                           Model model){
        String msg = "验证码错误或超时，重置密码失败";
        if (userService.checkCode(code)){
            userService.resetPwd(password);
            msg = "重置成功";
        }
        model.addAttribute("msg", msg);
        return "user/resetPwdResult";
    }


}
