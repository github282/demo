package com.cloudmusic.dao;

import com.cloudmusic.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findById(int id);
    public User findByUsername(String username);
    public User findByEmail(String email);

    @Query(value = "select u.* from user u, user_authority ua" +
            " where u.id=ua.user_id and ua.authority_id!=1", nativeQuery = true)
    public List<User> findUsers();
    @Query(value = "select id from user where username=?1", nativeQuery = true)
    public Integer findIdByUsername(String username);
    @Query(value = "select email from user where username=?1", nativeQuery = true)
    public String findEmailByUsername(String username);
}
