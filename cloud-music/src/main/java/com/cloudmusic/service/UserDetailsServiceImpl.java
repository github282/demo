package com.cloudmusic.service;

import com.cloudmusic.domian.Authority;
import com.cloudmusic.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUser(s);
        List<Authority> authorities = userService.getUserAuthority(s);

        List<SimpleGrantedAuthority> list = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        if (user!=null){
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), list);
            return userDetails;
        }else {
            throw new UsernameNotFoundException("当前用户不存在！");
        }
    }
}
