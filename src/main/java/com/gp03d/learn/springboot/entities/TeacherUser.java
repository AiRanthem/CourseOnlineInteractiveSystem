package com.gp03d.learn.springboot.entities;


public class TeacherUser
{
    /**
     * @Created by 李林禹 2019.7.9
     */

    private String TID;
    private String TPass;
    private  String Tname;

    public TeacherUser(String TID, String TPass, String Tname)
    {
        this.TID = TID;
        this.TPass = TPass;
        this.Tname=Tname;
    }

    public String getTID()
    {
        return TID;
    }

    public void setTID(String TID)
    {
        this.TID = TID;
    }

    public String getTPass()
    {
        return TPass;
    }

    public void setTPass(String TPass)
    {
        this.TPass = TPass;
    }

    public String getTname()
    {
        return Tname;
    }

    @Override
    public String toString()
    {
        return "TeacherUser{" +
                "TID='" + TID + '\'' +
                ", TPass='" + TPass + '\'' +
                '}';
    }
}
