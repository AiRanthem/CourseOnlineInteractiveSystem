package com.gp03d.learn.springboot.entities;

public class Msg {
    private String MID;
    private String UID;
    private String Username;
    private String Text;
    private String Time;

    @Override
    public String toString() {
        return "Msg{" +
                "MID=" + MID +
                ", UID='" + UID + '\'' +
                ", Username='" + Username + '\'' +
                ", Text='" + Text + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public Msg(String MID, String UID, String username, String text, String time) {
        this.MID = MID;
        this.UID = UID;
        Username = username;
        Text = text;
        Time = time;
    }
}
