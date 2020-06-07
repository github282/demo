package com.cloudmusic.service;

import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class CodeService {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    private String mailFrom = "zhuoj127@163.com";

    public void sendCodeMail(String username){
        Code code= new Code();
        code.setUsername(username);
        Date date = new Date();
        code.setCreateDate(date);
        //产生6位随机数
        Random random = new Random();
        String c = "";
        for (int i = 0; i < 6; i ++){
            String s = Integer.toString(random.nextInt(10));
            c += s;
        }
        code.setCode(c);
        codeDao.save(code);
        //发送邮件
        String mailTo = userDao.findEmailByUsername(username);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(mailTo);
        message.setSubject("您的Cloud Music账户正在进行密码重置");
        message.setText("验证码:" + code.getCode() + "\n五分钟内有效");
        mailSender.send(message);
    }

    public boolean checkCode(String username, String code, Date checkDate){
        Code c = codeDao.findByUsername(username);
        //验证码正确且在5分钟内验证
        if (code.equals(c.getCode()) && (checkDate.getTime() - c.getCreateDate().getTime() < 300000)){
            return true;
        }
        return false;
    }
}
