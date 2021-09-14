package com.zsy.springframework.test.bean;

import com.zsy.springframework.beans.factory.annotation.Autowired;
import com.zsy.springframework.beans.factory.annotation.Value;
import com.zsy.springframework.stereotype.Component;

import java.util.Random;

public class UserService implements IUserService{

    private String token;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳，" + token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }"; }
}
