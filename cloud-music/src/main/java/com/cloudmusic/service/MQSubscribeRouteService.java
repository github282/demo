package com.cloudmusic.service;

import com.cloudmusic.bean.UserDetailsBean;
import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Code;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class MQSubscribeRouteService {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    private static final String mailFrom = "zhuoj127@163.com";


    @RabbitListener(bindings =@QueueBinding(value =@Queue("routing_queue"),
            exchange =@Exchange(value = "routing_exchange",type = "direct"),
            key = "routing_mail"))
    public void routingConsumerError(String username) {
        //产生6位随机数
        Random random = new Random();
        String c = "";
        for (int i = 0; i < 6; i ++){
            String s = Integer.toString(random.nextInt(10));
            c += s;
        }
        //产生验证码，有效时间5分钟
        Code code = new Code(username, c, 300);
        codeDao.save(code);

        String mailTo = userDao.findEmailByUsername(username);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expireTime = formatter.format(code.getExpireTime());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(mailTo);
        message.setSubject("您的Cloud Music账户正在进行密码重置");
        message.setText("验证码:" + code.getCode() +
                "\n过期时间:" + expireTime);
        //发送邮件
        mailSender.send(message);
    }

}
