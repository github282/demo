package com.cloudmusic.service;

import com.cloudmusic.dao.MusicFeverDao;
import com.cloudmusic.domian.MusicFever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MusicFeverService {

    @Autowired
    private MusicFeverDao dao;

    public void addPlayedTimes(int musicId){
        MusicFever fever = dao.findByMusicId(musicId);
        fever.setWeekPlayedTimes(fever.getWeekPlayedTimes()+1);
        fever.setMonthPlayedTimes(fever.getMonthPlayedTimes()+1);
        dao.save(fever);
    }

    public List<MusicFever> getWeekTop(int units){
        Sort sort = Sort.by(Sort.Direction.DESC, "weekFever");
        return getTop(units, sort);
    }

    public List<MusicFever> getMonthTop(int units){
        Sort sort = Sort.by(Sort.Direction.DESC, "monthFever");
        return getTop(units, sort);
    }

    private List<MusicFever> getTop(int units, Sort sort){
        List<MusicFever> fevers = dao.findAll(sort);
        return fevers;
    }

    @Scheduled(cron = "* * * * * 0")//星期天进行
    public void countMusicWeekFever(){
        List<MusicFever> fevers = dao.findAll();
        LocalDate today = LocalDate.now();
        for (MusicFever fever : fevers){//计算热度
            int weekPlayedTimes = fever.getWeekPlayedTimes();
            long days = fever.getDate().until(today, ChronoUnit.DAYS);
            float hot = weekPlayedTimes - (days/100);
            fever.setWeekFever(hot);
            fever.setWeekPlayedTimes(0);//清空每周播放次数
            dao.save(fever);
        }
    }

    @Scheduled(cron = "* * * 1 * *")//每月1号进行
    public void countMusicMonthFever(){
        List<MusicFever> fevers = dao.findAll();
        LocalDate today = LocalDate.now();
        for (MusicFever fever : fevers){//计算热度
            int monthPlayedTimes = fever.getMonthPlayedTimes();
            long days = fever.getDate().until(today, ChronoUnit.DAYS);
            float hot = monthPlayedTimes - (days/100);
            fever.setWeekFever(hot);
            fever.setMonthPlayedTimes(0);
            dao.save(fever);
        }
    }
}
