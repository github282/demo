package com.cloudmusic.dao;

import com.cloudmusic.domian.MusicFever;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicFeverDao extends JpaRepository<MusicFever, Integer> {

    public MusicFever findByMusicId(int musicId);
}
