package com.cloudmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CloudMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicApplication.class, args);
    }

}
