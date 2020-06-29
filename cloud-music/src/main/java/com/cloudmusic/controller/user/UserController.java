package com.cloudmusic.controller.user;

import com.cloudmusic.bean.UserDetailsBean;
import com.cloudmusic.domian.User;
import com.cloudmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsBean userDetailsBean;

    @GetMapping("/info")
    public String info(Model model){
        String username = userDetailsBean.getUsername();
        User user = userService.findByUsername(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "user/info";
    }

    @GetMapping("/register")
    public String register(){
        return "login/register";
    }

    @ResponseBody
    @PostMapping("/register")
    public String register(String username,
                           String password,
                           String email){
        String msg = "注册成功";
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            userService.register(user);
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }

    /**
     * 处理ajax请求
     * 发送重置密码邮件
     * @return msg
     */
    @ResponseBody
    @GetMapping("/sendCodeToMail")
    public String sendCodeToMail(){
        String msg = userService.sendCodeToMail();
        return msg;
    }

    /**
     * 处理ajax请求
     * 发送重置密码邮件
     * @return msg
     */
    @ResponseBody
    @PostMapping("/sendCodeToMail")
    public String sendCodeToMail(String email){
        String msg = userService.sendCodeToMail(email);
        return msg;
    }

    @GetMapping("/forgetPwd")
    public String forgetPwd(){
        return "login/forgetPwd";
    }

    /**
     * 处理ajax请求
     * 重置密码
     * @return msg
     */
    @ResponseBody
    @PostMapping("/forgetPwd")
    public String forgerPwd(String email,
                            String code,
                            String password){
        String msg = "验证码错误或超时，重置密码失败";
        User user = userService.findByEmail(email);
        String username = user.getUsername();
        if (userService.checkCode(code, username)){
            userService.resetPwd(password, username);
            msg = "重置成功";
        }
        return msg;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(){
        return "user/resetPwd";
    }

    /**
     * 处理ajax请求
     * 重置密码
     * @return msg
     */
    @ResponseBody
    @PostMapping("/resetPwd")
    public String resetPwd(@RequestParam(value = "code") String code,
                           @RequestParam(value = "password") String password){
        String msg = "验证码错误或超时，重置密码失败";
        String username = userDetailsBean.getUsername();
        if (userService.checkCode(code, username)){
            userService.resetPwd(password, username);
            msg = "重置成功";
        }
        return msg;
    }

}
