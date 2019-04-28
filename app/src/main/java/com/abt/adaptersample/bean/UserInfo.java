package com.abt.adaptersample.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String accout;
    private String password;
    private int type;

    public UserInfo(String accout, String password) {
        this.accout = accout;
        this.password = password;
    }

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "accout = "+accout+", password = "+password;
    }
}
