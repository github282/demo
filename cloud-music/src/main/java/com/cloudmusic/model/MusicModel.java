package com.cloudmusic.model;

import com.cloudmusic.domian.Music;

public class MusicModel extends Music{

    private boolean isLove;//是否为用户收藏音乐

    public MusicModel(){}

    public MusicModel(Music music, boolean isLove){
        super(music);
        this.isLove = isLove;
    }

    public boolean isLove() {
        return isLove;
    }

    public void setLove(boolean love) {
        isLove = love;
    }

    @Override
    public String toString() {
        return "MusicModel{" +
                "isLove=" + isLove +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                ", vip=" + vip +
                ", path='" + path + '\'' +
                '}';
    }
}
