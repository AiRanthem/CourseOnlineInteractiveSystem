package com.gp03d.learn.springboot.entities;

public class GroupMsg {
    private Integer GID;
    private Integer MID;

    public GroupMsg(Integer GID, Integer MID) {
        this.GID = GID;
        this.MID = MID;
    }

    @Override
    public String toString() {
        return "GroupMsg{" +
                "GID=" + GID +
                ", MID=" + MID +
                '}';
    }

    public Integer getGID() {
        return GID;
    }

    public void setGID(Integer GID) {
        this.GID = GID;
    }

    public Integer getMID() {
      System.out.print(MID);
        return MID;
    }

    public void setMID(Integer MID) {
        this.MID = MID;
    }
}
