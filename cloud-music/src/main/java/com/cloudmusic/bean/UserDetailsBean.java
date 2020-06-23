package com.cloudmusic.bean;

import com.cloudmusic.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsBean {

    @Autowired
    private UserDao userDao;

    public String getUsername(){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails.getUsername();
    }

    public int getUserId(){
        return userDao.findIdByUsername(this.getUsername());
    }
}
