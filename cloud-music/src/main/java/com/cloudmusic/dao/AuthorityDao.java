package com.cloudmusic.dao;

import com.cloudmusic.domian.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
    @Query(value = "select a.* from user u, authority a, user_authority ua " +
            "where ua.user_id = u.id and ua.authority_id=a.id and u.username=?1", nativeQuery = true)
    public List<Authority> findAuthoritiesByUsername(String username);


    @Query(value = "select a.* from user u, authority a, user_authority ua " +
            "where ua.user_id = u.id and ua.authority_id=a.id and u.username=?1", nativeQuery = true)
    public Authority findAuthorityByUsername(String username);
}
