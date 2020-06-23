package com.cloudmusic.domian;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "code")
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String code;
    private LocalDateTime expireTime;

    public Code(){}

    public Code(String username, String code, int expireIn){
        this.username = username;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "Code{" +
                "username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
