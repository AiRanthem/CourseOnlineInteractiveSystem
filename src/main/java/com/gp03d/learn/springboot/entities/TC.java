package com.gp03d.learn.springboot.entities;

//老师开设的课程的表
public class TC
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String SID;
    private String TID;
    private String Cname;
    private String Tname;

    public TC(String SID, String TID, String cname, String tname)
    {
        this.SID = SID;
        this.TID = TID;
        Cname = cname;
        Tname = tname;
    }

    public String getSID()
    {
        return SID;
    }

    public void setSID(String SID)
    {
        this.SID = SID;
    }

    public String getTID()
    {
        return TID;
    }

    public void setTID(String TID)
    {
        this.TID = TID;
    }

    public String getCname()
    {
        return Cname;
    }

    public void setCname(String cname)
    {
        Cname = cname;
    }

    public String getTname()
    {
        return Tname;
    }

    public void setTname(String tname)
    {
        Tname = tname;
    }

    @Override
    public String toString()
    {
        return "TC{" +
                "SID='" + SID + '\'' +
                ", TID='" + TID + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Tname='" + Tname + '\'' +
                '}';
    }
}
