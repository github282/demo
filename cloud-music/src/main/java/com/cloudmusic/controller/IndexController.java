package com.cloudmusic.controller;

import com.cloudmusic.domian.Music;
import com.cloudmusic.service.MusicFeverService;
import com.cloudmusic.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicFeverService musicFeverService;

    @GetMapping("/")
    public String index(Model model){
        List<Integer> num = musicFeverService.getWeekTop(3);
        List<Music> musicList = new ArrayList<>();
        for (int i : num){
            musicList.add(musicService.findById(i));
        }
        model.addAttribute("list", musicList);
        return "index";
    }
}
