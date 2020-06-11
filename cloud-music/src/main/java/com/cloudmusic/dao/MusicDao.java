package com.cloudmusic.dao;

import com.cloudmusic.domian.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicDao extends JpaRepository<Music, Integer> {

    public List<Music> findByTitleLike(String title);
}
