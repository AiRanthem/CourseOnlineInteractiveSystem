package com.gp03d.learn.springboot.entities;

//选课信息&成绩信息
public class SCT
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String SID;
    private String CID;
    private String TID;
    private Integer Grade;
    private String Cname;
    private String Tname;

    public SCT(String SID, String CID, String TID, Integer grade, String cname, String tname)
    {
        this.SID = SID;
        this.CID = CID;
        this.TID = TID;
        Grade = grade;
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

    public String getCID()
    {
        return CID;
    }

    public void setCID(String CID)
    {
        this.CID = CID;
    }

    public String getTID()
    {
        return TID;
    }

    public void setTID(String TID)
    {
        this.TID = TID;
    }

    public Integer getGrade()
    {
        return Grade;
    }

    public void setGrade(Integer grade)
    {
        Grade = grade;
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
        return "SCT{" +
                "SID='" + SID + '\'' +
                ", CID='" + CID + '\'' +
                ", TID='" + TID + '\'' +
                ", Grade=" + Grade +
                ", Cname='" + Cname + '\'' +
                ", Tname='" + Tname + '\'' +
                '}';
    }
}
