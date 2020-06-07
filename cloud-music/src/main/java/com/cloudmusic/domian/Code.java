package com.cloudmusic.domian;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "code")
public class Code implements Serializable {

    @Id
    private String username;
    private String code;
    private Date createDate;

    @Override
    public String toString() {
        return "Code{" +
                "username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", createDate=" + createDate +
                '}';
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
