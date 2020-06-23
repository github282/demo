package com.cloudmusic.domian;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity(name = "music")
public class Music implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String title;//标题
    protected String artist;//艺术家
    protected String album;//专辑
    protected String duration;//时长
    protected Integer vip;//vip专属
    protected String path;//文件路径

    public Music(){};

    public Music(String title, String artist, String album,Time duration, Integer vip, String path){
        this.title = title;
        this.artist = artist;
        this.album = album;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        this.duration = sdf.format(duration);
        this.vip = vip;
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                ", vip=" + vip +
                ", path='" + path + '\'' +
                '}';
    }
}
