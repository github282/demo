package com.cloudmusic.service;

import com.cloudmusic.bean.UserDetailsBean;
import com.cloudmusic.dao.MusicDao;
import com.cloudmusic.dao.MusicFeverDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.dao.UserMusicDao;
import com.cloudmusic.domian.Music;
import com.cloudmusic.domian.MusicFever;
import com.cloudmusic.domian.UserMusic;
import com.cloudmusic.entity.Mp3.Mp3Info;
import com.cloudmusic.model.MusicModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    private static final String musicDirPath = "D:/CloudMusicFile/";

    @Autowired
    private MusicDao musicDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMusicDao userMusicDao;
    @Autowired
    private UserDetailsBean userDetailsBean;
    @Autowired
    private MusicFeverDao musicFeverDao;

    /**
     * 需要解决最终一致性问题
     * @param file
     * @param limit
     * @return msg
     */
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
        int id = musicDao.save(music).getId();
        MusicFever fever = new MusicFever(id);
        musicFeverDao.save(fever);
        return "上传成功";
    }

    private List<MusicModel> createModels(List<Music> musicList){
        //获取用户收藏的音乐id
        String username = userDetailsBean.getUsername();
        int user_id = userDao.findIdByUsername(username);
        List<Integer> idList = userMusicDao.findMusicIdByUserId(user_id );
        //合并
        List<MusicModel> models = new ArrayList<>();
        for (Music music : musicList){
            boolean isLove = false;
            for (Integer id : idList){
                if (music.getId()==id){
                    isLove = true;
                }
            }
            MusicModel model = new MusicModel(music, isLove);
            models.add(model);
        }
        return models;
    }

    public List<MusicModel> findByTitleLike(String key){
        //获取搜索到的音乐
        List<Music> musicList = musicDao.findByTitleLike("%"+key+"%");
        return createModels(musicList);
    }

    public Music findById(int id){
        return musicDao.findById(id);
    }

    public boolean isUserLove(int musicId){
        if (userMusicDao.findByUserIdAndMusicId(userDetailsBean.getUserId(), musicId)==null){
            return false;
        }else {
            return true;
        }
    }

    public List<MusicModel> addLove(int musicId){
        UserMusic userMusic = new UserMusic(userDetailsBean.getUserId(), musicId);
        userMusicDao.save(userMusic);

        List<Music> musicList = new ArrayList<>();
        musicList.add(musicDao.findById(musicId));
        return createModels(musicList);
    }

    public List<MusicModel> removeLove(int musicId){
        userMusicDao.deleteByUserIdAndMusicId(userDetailsBean.getUserId(), musicId);

        List<Music> musicList = new ArrayList<>();
        musicList.add(musicDao.findById(musicId));
        return createModels(musicList);
    }

    public List<MusicModel> findUserMusic(){
        String username = userDetailsBean.getUsername();
        List<Music> musicList = musicDao.findUserMusic(username);
        return createModels(musicList);
    }

}
