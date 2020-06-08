package com.cloudmusic.service;

import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domain.Code;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MQSubscribeTopicService {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    private String mailFrom = "zhuoj127@163.com";

    /**
     *  通配符模式消息接收，进行邮件业务订阅处理
     * @param username
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("topic_queue_email"),
            exchange =@Exchange(value = "topic_exchange",type = "topic"),
            key = "info.#.email.#"))
    public void topicConsumerEmail(String username) {
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
        //发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(userDao.findEmailByUsername(username));
        message.setSubject("您的Cloud Music账户正在进行密码重置");
        message.setText("验证码:" + code.getCode() + "\n过期时间:" + code.getExpireTime());
        mailSender.send(message);
    }

}