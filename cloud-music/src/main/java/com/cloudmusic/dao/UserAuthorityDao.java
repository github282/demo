package com.cloudmusic.dao;

import com.cloudmusic.domian.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityDao extends JpaRepository<UserAuthority, Integer> {

    public UserAuthority findUserAuthorityByUserId(int userId);
}
