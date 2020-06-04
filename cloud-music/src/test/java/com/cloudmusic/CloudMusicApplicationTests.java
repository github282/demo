package com.cloudmusic;

import com.cloudmusic.dao.AuthorityDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.domain.Authority;
import com.cloudmusic.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CloudMusicApplicationTests {

    @Autowired
    CodeService codeService;
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        Date date = new Date();// 创建Date类型对象

        // 创建SimpleDateFormat类型对象、 "yyyy-MM-dd HH:ss:mm.SSS"是正则式，分别表示年月日时分秒毫秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 定义两个时间
        String startTime = "2018-8-21 20:00:00";
        String endTime = "2018-8-21 20:05:00";

        try {
            // 将两个String类型的时间转换为Date类型，从而计算差值、parse()方法的作用是将String类型的时间解析为Date类型
            Date d1 = sdf.parse(startTime);
            Date d2 = sdf.parse(endTime);
            System.out.println(d2.getTime() - d1.getTime());
        }catch (ParseException e){

        }
    }

}
