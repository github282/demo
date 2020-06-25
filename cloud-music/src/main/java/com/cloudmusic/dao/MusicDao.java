package com.cloudmusic.dao;

import com.cloudmusic.domian.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicDao extends JpaRepository<Music, Integer> {

    public List<Music> findByTitleLike(String title);

    @Query(value = "select m.* from user_music um, music m, user u " +
            "where u.id = um.user_id and um.music_id = m.id and u.username=?1", nativeQuery = true)
    public List<Music> findUserMusic(String username);

    public Music findById(int id);
}
