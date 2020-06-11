package com.cloudmusic.controller;

import com.cloudmusic.domian.Music;
import com.cloudmusic.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/music/findByTitleLike")
    public String selectMusic(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "6") int pageSize,
                              String key,
                              Model model){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(musicService.findByTitleLike(key));
        model.addAttribute("key", key);
        model.addAttribute("pageInfo", pageInfo);
        return "selectMusic";
    }

    @GetMapping("/upload/music")
    public String uploadMusic(){
        return "user/admin/uploadMusic";
    }

    @ResponseBody
    @PostMapping("/upload/music")
    public String uploadMusic(MultipartFile file, String limit){
        Integer l = Integer.valueOf(limit);
        String msg = musicService.uploadMusic(file, l);
        return msg;
    }
}
