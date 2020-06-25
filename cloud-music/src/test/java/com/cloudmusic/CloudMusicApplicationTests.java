package com.cloudmusic;

import com.cloudmusic.dao.CodeDao;
import com.cloudmusic.dao.MusicFeverDao;
import com.cloudmusic.dao.UserDao;
import com.cloudmusic.dao.UserMusicDao;
import com.cloudmusic.domian.Code;
import com.cloudmusic.domian.MusicFever;
import com.cloudmusic.domian.User;
import com.cloudmusic.model.UserModel;
import com.cloudmusic.service.AdminService;
import com.cloudmusic.service.MusicFeverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CloudMusicApplicationTests {

    @Autowired
    private CodeDao codeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    private String mailFrom = "zhuoj127@163.com";

    @Test
    void contextLoads() {
        String username = "user003";
        printTime("开始产生6位随机数");
        //产生6位随机数
        Random random = new Random();
        String c = "";
        for (int i = 0; i < 6; i ++){
            String s = Integer.toString(random.nextInt(10));
            c += s;
        }
        printTime("开始产生验证码，有效时间5分钟");
        //产生验证码，有效时间5分钟
        Code code = new Code(username, c, 300);
        codeDao.save(code);
        //发送邮件
        printTime("开始从数据库中查询邮箱");
        String mailTo = userDao.findEmailByUsername(username);
        printTime("结束从数据库中查询邮箱");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expireTime = formatter.format(code.getExpireTime());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(mailTo);
        message.setSubject("您的Cloud Music账户正在进行密码重置");
        message.setText("验证码:" + code.getCode() + "\n过期时间:" + expireTime);
        printTime("开始发送邮件");
        mailSender.send(message);
        printTime("结束");
    }

    private void printTime(String msg){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(msg);
        System.out.println(formatter.format(time));
    }

    @Test
    void countLocalDate(){
        LocalDate date1 = LocalDate.parse("2019-01-01");
        LocalDate date2 = LocalDate.parse("2020-01-13");
        long days = date1.until(date2, ChronoUnit.DAYS);
        System.out.println(days);
    }

    @Autowired
    private MusicFeverService musicFeverService;
    @Test
    void fever(){
        musicFeverService.countMusicMonthFever();
        musicFeverService.countMusicWeekFever();
    }

    @Autowired
    private MusicFeverDao musicFeverDao;
    @Test
    void feverOrder(){
        List<Integer> weekFevers = musicFeverDao.findWeekTop(5);
        System.out.println("周榜");
        for (Integer fever : weekFevers){
            System.out.println(fever);
        }

        System.out.println("月榜");
        List<Integer> monthFevers = musicFeverDao.findMonthTop(5);
        for (Integer fever : monthFevers){
            System.out.println(fever);
        }
    }

    @Test
    void str(){
        String filePath = "D:\\CloudMusic\\song.mp3";
        String[] str1 = filePath.split("\\\\");
        String str2 = str1[str1.length-1];
        String[] str3 = str2.split("\\.");
        String title = str3[0];
        System.out.println(title);
    }

    @Autowired
    private AdminService adminService;
    @Test
    void findAllUsers(){
        List<UserModel> users = adminService.findAllUsers();
        for (User user : users){
            System.out.println(user);
        }
    }
}
