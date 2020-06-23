package com.cloudmusic.service;

import com.cloudmusic.bean.EncoderPwd;
import com.cloudmusic.bean.UserDetailsBean;
import com.cloudmusic.dao.AuthorityDao;
import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.UserAuthorityDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Authority;
import com.cloudmusic.domian.Code;
import com.cloudmusic.domian.User;
import com.cloudmusic.domian.UserAuthority;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;
    @Autowired
    private UserDetailsBean userDetailsBean;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private EncoderPwd encoderPwd;

    public User getUser(String username){
        User user = null;
        Object o = redisTemplate.opsForValue().get("user_" + username);
        if (o != null) {
            user = (User)o;
        }else {
            user = userDao.findByUsername(username);
            if (user!=null){
                redisTemplate.opsForValue().set("user_" + username, user);
            }
        }
        return user;
    }

    public List<Authority> getUserAuthority(String username){
        List<Authority> authorities = null;
        Object o = redisTemplate.opsForValue().get("authority_" + username);
        if (o!=null){
            authorities = (List<Authority>)o;
        }else {
            authorities = authorityDao.findAuthorityByUsername(username);
            if (authorities.size()>0){
                redisTemplate.opsForValue().set("authority_" + username, authorities);
            }
        }
        return authorities;
    }

    public void register(User u){
        //对密码进行加密
        u.setPassword(encoderPwd.encoderPassword(u.getPassword()));
        //创建注册日期
        Date date = new Date();
        u.setRegistrationDate(date);
        u.setValid(1);
        //将注册用户写入user数据库
        userDao.save(u);
        //将注册用户的权限写入user_authority数据库
        int userId = userDao.findIdByUsername(u.getUsername());
        UserAuthority userAuthority = new UserAuthority(userId);
        userAuthorityDao.save(userAuthority);
    }

    public void resetPwd(String password){
        User user = userDao.findByUsername(userDetailsBean.getUsername());
        user.setPassword(encoderPwd.encoderPassword(password));
        userDao.save(user);
        redisTemplate.opsForValue().getAndSet("user_"+user.getUsername(), user);
    }

    public String sendCodeMail(){
        String msg = "success";
        try {
            String username = userDetailsBean.getUsername();
            rabbitTemplate.convertAndSend("routing_exchange", "routing_mail", username);
        }catch (Exception e){
            e.printStackTrace();
            msg = "fail";
        }
        return msg;
    }

    public boolean checkCode(String code){
        Code c = codeDao.findByUsername(userDetailsBean.getUsername());
        LocalDateTime checkDate = LocalDateTime.now();
        //验证码正确且在规定时间内进行了验证
        if (code.equals(c.getCode()) && (checkDate.isBefore(c.getExpireTime()))){
            return true;
        }
        return false;
    }
}
