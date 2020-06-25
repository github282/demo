package com.cloudmusic.model;

import com.cloudmusic.domian.User;

public class UserModel extends User {
    private boolean valid;
    private String authority;

    public UserModel(User user, String authority){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.registrationDate = user.getRegistrationDate();
        if (user.getValid()==1){this.valid = true;}
        else {this.valid = false;}
        this.authority = authority;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "valid=" + valid +
                ", authority='" + authority + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
