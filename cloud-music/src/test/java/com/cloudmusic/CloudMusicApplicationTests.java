package com.cloudmusic;

import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Code;
import com.cloudmusic.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@SpringBootTest
class CloudMusicApplicationTests {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    private String mailFrom = "zhuoj127@163.com";

    @Test
    void contextLoads() {
        String username = "user003";
        printTime("开始产生6位随机数");
        //产生6位随机数
        Random random = new Random();
        String c = "";
        for (int i = 0; i < 6; i ++){
            String s = Integer.toString(random.nextInt(10));
            c += s;
        }
        printTime("开始产生验证码，有效时间5分钟");
        //产生验证码，有效时间5分钟
        Code code = new Code(username, c, 300);
        codeDao.save(code);
        //发送邮件
        printTime("开始从数据库中查询邮箱");
        String mailTo = userDao.findEmailByUsername(username);
        printTime("结束从数据库中查询邮箱");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expireTime = formatter.format(code.getExpireTime());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(mailTo);
        message.setSubject("您的Cloud Music账户正在进行密码重置");
        message.setText("验证码:" + code.getCode() + "\n过期时间:" + expireTime);
        printTime("开始发送邮件");
        mailSender.send(message);
        printTime("结束");
    }

    private void printTime(String msg){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(msg);
        System.out.println(formatter.format(time));
    }

}
