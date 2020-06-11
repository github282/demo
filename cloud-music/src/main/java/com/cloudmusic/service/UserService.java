package com.cloudmusic.service;

import com.cloudmusic.bean.EncoderPwd;
import com.cloudmusic.dao.AuthorityDao;
import com.cloudmusic.dao.UserAuthorityDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Authority;
import com.cloudmusic.domian.User;
import com.cloudmusic.domian.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EncoderPwd encoderPwd;

    public User getUser(String username){
        User user = null;
        Object o = redisTemplate.opsForValue().get("user_"+username);
        if (o != null) {
            user = (User)o;
        }else {
            user = userDao.findByUsername(username);
            if (user!=null){
                redisTemplate.opsForValue().set("user_"+username,user);
            }
        }
        return user;
    }

    public List<Authority> getUserAuthority(String username){
        List<Authority> authorities = null;
        Object o = redisTemplate.opsForValue().get("authority_"+username);
        if (o!=null){
            authorities = (List<Authority>)o;
        }else {
            authorities = authorityDao.findAuthorityByUsername(username);
            if (authorities.size()>0){
                redisTemplate.opsForValue().set("authority_"+username,authorities);
            }
        }
        return authorities;
    }

    public void register(User u){
        User user = u;
        //对密码进行加密
        user.setPassword(encoderPwd.encoderPassword(u.getPassword()));
        //创建注册日期
        Date date = new Date();
        user.setRegistrationDate(date);
        user.setValid(1);
        //将注册用户写入user数据库
        userDao.save(u);
        //将注册用户的权限写入user_authority数据库
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser_id(userDao.findIdByUsername(user.getUsername()));
        userAuthority.setAuthority_id(3);
        userAuthorityDao.save(userAuthority);
    }

}
