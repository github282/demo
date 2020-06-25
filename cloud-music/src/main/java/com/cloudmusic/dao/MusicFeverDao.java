package com.cloudmusic.dao;

import com.cloudmusic.domian.MusicFever;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicFeverDao extends JpaRepository<MusicFever, Integer> {

    public MusicFever findByMusicId(int musicId);

    @Query(value="select music_id from music_fever order by week_fever desc limit 0,?1", nativeQuery=true)
    public List<Integer> findWeekTop(int num);

    @Query(value="select music_id from music_fever order by month_fever desc limit 0,?1", nativeQuery=true)
    public List<Integer> findMonthTop(int num);
}
