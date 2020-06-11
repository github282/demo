package com.cloudmusic.service;

import com.cloudmusic.bean.EncoderPwd;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EncoderPwd encoderPwd;

    public void resetPwd(String username, String password){
        User user = userDao.findByUsername(username);
        user.setPassword(encoderPwd.encoderPassword(password));
        userDao.save(user);
    }

}
