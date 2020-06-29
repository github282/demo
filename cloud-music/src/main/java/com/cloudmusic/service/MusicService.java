package com.cloudmusic.service;

import com.cloudmusic.bean.MusicDir;
import com.cloudmusic.bean.UserDetailsBean;
import com.cloudmusic.dao.MusicDao;
import com.cloudmusic.dao.MusicFeverDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.dao.UserMusicDao;
import com.cloudmusic.domian.Music;
import com.cloudmusic.domian.MusicFever;
import com.cloudmusic.domian.UserMusic;
import com.cloudmusic.entity.mp3.Mp3Info;
import com.cloudmusic.model.MusicModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicDir path;
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

    public String uploadMusic(MultipartFile mFile, Integer vip){

        String musicDirPath = path.getDirPath();

        //创建文件目录
        File dir = new File(musicDirPath);
        if (!dir.exists()){
            dir.mkdir();
        }
        //获取文件名并上传文件
        String filename = mFile.getOriginalFilename();
        try {
            mFile.transferTo(new File(musicDirPath + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

        File file = new File(musicDirPath + filename);
        Mp3Info mp3Info = new Mp3Info().getMusicInfo(file);
        Music music = new Music(mp3Info, vip, filename);
        int id = musicDao.save(music).getId();
        MusicFever fever = new MusicFever(id);
        musicFeverDao.save(fever);
        return "上传成功";
    }


    private List<MusicModel> createModels(List<Music> musicList){
        List<MusicModel> models = new ArrayList<>();
        String username = null;
        try {
            username = userDetailsBean.getUsername();
        }catch (ClassCastException e){
//            System.out.println("找不到用户信息");
        }
        if (username != null){
            //获取用户收藏的音乐id
            int user_id = userDao.findIdByUsername(username);
            List<Integer> idList = userMusicDao.findMusicIdByUserId(user_id );
            //合并
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
        }else {
            for (Music music : musicList){
                MusicModel model = new MusicModel(music, false);
                models.add(model);
            }
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
