package com.cloudmusic.domian;

import com.cloudmusic.entity.mp3.Mp3Info;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "music")
public class Music extends Mp3Info implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected Integer vip;//vip专属
    protected String path;//文件路径

    public Music(){};

    public Music(Music music){
        this.id = music.getId();
        this.title = music.getTitle();
        this.album = music.getAlbum();
        this.artist = music.getArtist();
        this.duration = music.getDuration();
        this.vip = music.getVip();
        this.path = music.getPath();
    }

    public Music(Mp3Info info, Integer vip, String path){
        super(info);
        this.vip = vip;
        this.path = path;
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
                ", vip=" + vip +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}
