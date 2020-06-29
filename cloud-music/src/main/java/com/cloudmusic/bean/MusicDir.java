package com.cloudmusic.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music-dir")
public class MusicDir {

    private String dirPath;

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    @Override
    public String toString() {
        return "MusicDir{" +
                "dirPath='" + dirPath + '\'' +
                '}';
    }
}
