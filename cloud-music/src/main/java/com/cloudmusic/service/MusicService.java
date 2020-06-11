package com.cloudmusic.service;

import com.cloudmusic.dao.MusicDao;
import com.cloudmusic.domian.Music;
import com.cloudmusic.entity.Mp3File.Mp3Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

@Service
public class MusicService {

    private static final String musicDirPath = "D:/CloudMusicFile/";

    @Autowired
    private MusicDao musicDao;

    public String uploadMusic(MultipartFile file, Integer limit){
        //创建文件目录
        File dir = new File(musicDirPath);
        if (!dir.exists()){
            dir.mkdir();
        }
        //获取文件名并上传文件
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File(musicDirPath + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

        Mp3Info mp3Info = new Mp3Info().getMusicInfo(musicDirPath+filename);

        String title = mp3Info.getTitle();//标题
        String artist = mp3Info.getArtist();//艺术家
        String album = mp3Info.getAlbum();//专辑
        Time duration = mp3Info.getDuration();//时长
        String path =  filename;//文件路径

        Music music = new Music(title, artist, album, duration, limit, path);
        musicDao.save(music);
        return "上传成功";
    }

    public List<Music> findByTitleLike(String key){
        List<Music> musicList = musicDao.findByTitleLike("%" + key + "%");
        return musicList;
    }

}
