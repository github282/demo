package com.cloudmusic.domian;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_authority")
public class UserAuthority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "authority_id")
    private Integer authorityId;

    public UserAuthority(){}

    public UserAuthority(Integer userId){
        this.userId = userId;
        this.authorityId = 3;
    }

    public UserAuthority(Integer userId, Integer authorityId){
        this.userId = userId;
        this.authorityId = authorityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "id=" + id +
                ", userId=" + userId +
                ", authorityId=" + authorityId +
                '}';
    }
}
