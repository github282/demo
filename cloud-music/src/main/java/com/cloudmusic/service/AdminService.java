package com.cloudmusic.service;

import com.cloudmusic.dao.AuthorityDao;
import com.cloudmusic.dao.UserAuthorityDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.Authority;
import com.cloudmusic.domian.User;
import com.cloudmusic.domian.UserAuthority;
import com.cloudmusic.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;
    @Autowired
    private RedisTemplate redisTemplate;

    private UserModel createUserModel(User user){
        Authority authority = authorityDao.findAuthorityByUsername(user.getUsername());
        return new UserModel(user, authority.getAuthority());
    }

    public List<UserModel> findAllUsers(){
        List<User> users = userDao.findUsers();
        List<UserModel> models = new ArrayList<>();
        for (User user : users){
            UserModel model = createUserModel(user);
            models.add(model);
        }
        return models;
    }

    public UserModel activateUser(int userId){
        User user = userDao.findById(userId);
        if (user.getValid()==1){
            user.setValid(0);
        }else {
            user.setValid(1);
        }
        userDao.save(user);
        redisTemplate.opsForValue().getAndSet("user_"+user.getUsername(), user);
        return createUserModel(user);
    }

    public UserModel changeUserAuthority(int userId){
        UserAuthority userAuthority = userAuthorityDao.findUserAuthorityByUserId(userId);
        int authorityId = userAuthority.getAuthorityId();
        switch (authorityId){
            case 2:
                authorityId = 3;
                break;
            case 3:
                authorityId = 2;
                break;
            default:
                break;
        }
        userAuthority.setAuthorityId(authorityId);
        userAuthorityDao.save(userAuthority);

        User user = userDao.findById(userId);
        Authority authority = authorityDao.findAuthorityByUsername(user.getUsername());
        redisTemplate.opsForValue().getAndSet("authority_"+user.getUsername(), authority);
        return createUserModel(user);
    }
}
