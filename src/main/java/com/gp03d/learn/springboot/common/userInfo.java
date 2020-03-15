package com.gp03d.learn.springboot.common;

public class userInfo {
    public static String username;
    public static int usertype; // 1. teacher 0. student

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
            userInfo.username = username;
    }

    public static int getUsertype() {
        return usertype;
    }

    public static void setUsertype(int usertype) {
        userInfo.usertype = usertype;
    }
}
