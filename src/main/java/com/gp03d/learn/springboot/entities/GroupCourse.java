package com.gp03d.learn.springboot.entities;

public class GroupCourse {
    private String GID;
    private String CID;
    private String Gname;

    @Override
    public String toString() {
        return "GroupCourse{" +
                "GID='" + GID + '\'' +
                ", CID='" + CID + '\'' +
                ", Gname='" + Gname + '\'' +
                '}';
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public GroupCourse(String GID, String CID, String gname) {
        this.GID = GID;
        this.CID = CID;
        Gname = gname;
    }
}
