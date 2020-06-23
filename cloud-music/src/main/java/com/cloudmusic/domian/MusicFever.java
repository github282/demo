package com.cloudmusic.domian;

import com.sun.mail.imap.protocol.FLAGS;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "music_fever")
public class MusicFever {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "music_id")
    private Integer musicId;
    @Column(name = "upload_date")
    private LocalDate date;
    @Column(name = "week_played_times")
    private Integer weekPlayedTimes;
    @Column(name = "week_fever")
    private float weekFever;
    @Column(name = "month_played_times")
    private Integer monthPlayedTimes;
    @Column(name = "month_fever")
    private float monthFever;

    public MusicFever(){}

    public MusicFever(Integer musicId){
        this.musicId = musicId;
        this.date = LocalDate.now();
        this.weekPlayedTimes = 0;
        this.weekFever = 0;
        this.monthPlayedTimes = 0;
        this.monthFever = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getWeekPlayedTimes() {
        return weekPlayedTimes;
    }

    public void setWeekPlayedTimes(Integer weekPlayedTimes) {
        this.weekPlayedTimes = weekPlayedTimes;
    }

    public Integer getMonthPlayedTimes() {
        return monthPlayedTimes;
    }

    public void setMonthPlayedTimes(Integer monthPlayedTimes) {
        this.monthPlayedTimes = monthPlayedTimes;
    }

    public float getWeekFever() {
        return weekFever;
    }

    public void setWeekFever(float weekFever) {
        this.weekFever = weekFever;
    }

    public float getMonthFever() {
        return monthFever;
    }

    public void setMonthFever(float monthFever) {
        this.monthFever = monthFever;
    }

    @Override
    public String toString() {
        return "MusicFever{" +
                "id=" + id +
                ", musicId=" + musicId +
                ", date=" + date +
                ", weekPlayedTimes=" + weekPlayedTimes +
                ", weekFever=" + weekFever +
                ", monthPlayedTimes=" + monthPlayedTimes +
                ", monthFever=" + monthFever +
                '}';
    }
}
