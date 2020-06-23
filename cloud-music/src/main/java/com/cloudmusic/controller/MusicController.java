package com.cloudmusic.controller;

import com.cloudmusic.domian.Music;
import com.cloudmusic.domian.MusicFever;
import com.cloudmusic.model.MusicModel;
import com.cloudmusic.service.MusicFeverService;
import com.cloudmusic.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicFeverService musicFeverService;

    @ResponseBody
    @GetMapping("/top")
    public List<MusicFever> getTop(){
        List<MusicFever> weekTop = musicFeverService.getWeekTop(5);
        List<MusicFever> monthTop = musicFeverService.getMonthTop(5);
        List<MusicFever> top = weekTop;
        for (MusicFever fever : monthTop){
            top.add(fever);
        }
        return top;
    }

    @PostMapping("/findByTitleLike")
    public String selectMusic(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "6") int pageSize,
                              String key,
                              Model model){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = null;
        try {
            pageInfo = new PageInfo(musicService.findByTitleLike(key));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        model.addAttribute("key", key);
        model.addAttribute("pageInfo", pageInfo);
        return "selectMusic";
    }

    @GetMapping("/myMusic")
    public String findUserMusic(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "6") int pageSize,
                                Model model){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(musicService.findUserMusic());
        model.addAttribute("pageInfo", pageInfo);
        return "selectMusic";
    }

    /**
     * 处理ajax请求
     * @param musicId
     * @return
     */
    @ResponseBody
    @PostMapping("/play")
    public String play(String musicId) {
        int id = Integer.valueOf(musicId);
        Music music = musicService.findById(id);
        String musicPath = "/musicResource/" + music.getPath();
        musicFeverService.addPlayedTimes(id);
        return musicPath;
    }

    /**
     * 处理ajax请求
     * @param musicId
     * @return
     */
    @ResponseBody
    @PostMapping("/love")
    public boolean loverMusic(String musicId){
        List<MusicModel> models;
        int id = Integer.valueOf(musicId);
        if (musicService.isUserLove(id)){
            models = musicService.removeLove(id);
        }else {
            models = musicService.addLove(id);
        }
        MusicModel model = models.get(0);
        return model.isLove();
    }


    @GetMapping("/upload")
    public String uploadMusic(){
        return "user/admin/uploadMusic";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String uploadMusic(MultipartFile file, String vip){
        Integer v = Integer.valueOf(vip);
        String msg = musicService.uploadMusic(file, v);
        return msg;
    }
}
