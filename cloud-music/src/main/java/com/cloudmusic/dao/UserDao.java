package com.cloudmusic.dao;

import com.cloudmusic.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

    @Query(value = "select id from user where username=?1", nativeQuery = true)
    public Integer findIdByUsername(String username);
    @Query(value = "select email from user where username=?1", nativeQuery = true)
    public String findEmailByUsername(String username);
}
