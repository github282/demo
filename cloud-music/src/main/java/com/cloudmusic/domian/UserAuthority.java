package com.cloudmusic.domian;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "user_authority")
public class UserAuthority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer authority_id;

    public UserAuthority(){}

    public UserAuthority(Integer user_id){
        this.user_id = user_id;
        this.authority_id = 3;
    }

    public UserAuthority(Integer user_id, Integer authority_id){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    @Override
    public String toString() {
        return "UA{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", authority_id=" + authority_id +
                '}';
    }
}