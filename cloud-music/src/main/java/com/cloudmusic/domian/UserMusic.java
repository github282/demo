package com.cloudmusic.domian;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_music")
public class UserMusic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "music_id")
    private Integer musicId;

    public UserMusic(){}

    public UserMusic(int userId, int musicId){
        this.userId = userId;
        this.musicId = musicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    @Override
    public String toString() {
        return "UserMusic{" +
                "id=" + id +
                ", userId=" + userId +
                ", musicId=" + musicId +
                '}';
    }
}
