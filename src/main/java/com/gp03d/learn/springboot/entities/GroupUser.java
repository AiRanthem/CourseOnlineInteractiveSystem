package com.gp03d.learn.springboot.entities;

public class GroupUser {
    private Integer GID;
    private String UID;

    @Override
    public String toString() {
        return "GroupUser{" +
                "GID=" + GID +
                ", UID='" + UID + '\'' +
                '}';
    }

    public GroupUser(Integer GID, String UID) {
        this.GID = GID;
        this.UID = UID;
    }

    public Integer getGID() {
        return GID;
    }

    public void setGID(Integer GID) {
        this.GID = GID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
