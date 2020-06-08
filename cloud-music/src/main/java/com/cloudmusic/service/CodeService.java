package com.cloudmusic.service;

import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.domian.Code;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CodeService {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCodeMail(String username){
        //将发送邮件添加到队列
        rabbitTemplate.convertAndSend("topic_exchange",
                "info.email",username);
    }

    public boolean checkCode(String username, String code){
        Code c = codeDao.findByUsername(username);
        LocalDateTime checkDate = LocalDateTime.now();
        //验证码正确且在规定时间内进行了验证
        if (code.equals(c.getCode()) && (checkDate.isBefore(c.getExpireTime()))){
            return true;
        }
        return false;
    }
}
