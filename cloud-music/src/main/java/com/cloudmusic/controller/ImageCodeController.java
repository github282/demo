package com.cloudmusic.controller;

import com.cloudmusic.entity.imageCode.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ImageCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/img")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //产生验证码图片
        ImageCode imageCode = createImageCode();
        //将验证码存入session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        //将验证码的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());

    }

    //产生验证码图片
    private ImageCode createImageCode(){
        int width = 100;
        int height = 30;
        //创建一个不带透明色的BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        g.setColor(getRandColor(100,255));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman ", Font.ITALIC, 20));

        Random random = new Random();
        //产生随机线条

        for (int i = 0; i < 155; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.setColor(getRandColor(100,255));
            g.drawLine(x,y,x+xl,y+yl);
        }
        //产生随机数
        String sRand = "";
        for (int i = 0; i < 4; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand,20 * i + 10,random.nextInt(10)+20);
        }
        g.dispose();

        return new ImageCode(image, sRand, 60);
    }

    //生成随机颜色
    private Color getRandColor(int fc, int bc){
        Random random = new Random();
        if (fc > 255){fc = 255;}
        if (bc > 255){bc = 255;}
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b= fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
