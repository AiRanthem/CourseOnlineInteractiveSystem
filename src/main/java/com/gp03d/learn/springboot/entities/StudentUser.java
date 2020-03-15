package com.gp03d.learn.springboot.entities;


public class StudentUser
{
    /**
     * @Created by 李林禹 2019.7.9
     */

    private String SID;
    private String Spass;
    private  String Sname;


    public StudentUser(String spass, String SID,String Sname)
    {
        Spass = spass;
        this.SID = SID;
        this.Sname=Sname;
    }

    public String getSpass()
    {
        return Spass;
    }

    public void setSpass(String spass)
    {
        Spass = spass;
    }

    public String getSID()
    {
        return SID;
    }

    public void setSID(String SID)
    {
        this.SID = SID;
    }

    public String getSname()
    {
        return Sname;
    }

    @Override
    public String toString()
    {
        return "StudentUser{" +
                "Spass='" + Spass + '\'' +
                ", SID='" + SID + '\'' +
                '}';
    }
}
