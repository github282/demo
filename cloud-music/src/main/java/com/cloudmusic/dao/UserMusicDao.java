package com.cloudmusic.dao;

import com.cloudmusic.domian.UserMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

public interface UserMusicDao extends JpaRepository<UserMusic, Integer> {

    public UserMusic findByUserIdAndMusicId(Integer userId, Integer musicId);

    @Query(value = "select music_id from user_music where user_id=?1", nativeQuery = true)
    public List<Integer> findMusicIdByUserId(Integer userId);

    @Transactional
    public void deleteByUserIdAndMusicId(Integer userId, Integer musicId);
}
