package com.gp03d.learn.springboot.entities;

//课程
public class Course
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String CID;
    private String Cname;
    private Integer Ccredit;
    private String Cstart_time;
    private String Cend_time;
    private String Csite;
    private Integer Cnum;//选课人数
    private Integer Ctype;//Ctype没定义成枚举型变量。1，2，3，4分别对应院系必修课，院系选修课，全校选修课，公共课
    private Integer Climit;
    private String Cremark;
    private String Tname;
    private String TID;

    public Course(String CID, String cname, Integer ccredit, String cstart_time, String cend_time, String csite, Integer cnum, Integer ctype, Integer climit, String cremark, String tname, String TID) {
        this.CID = CID;
        Cname = cname;
        Ccredit = ccredit;
        Cstart_time = cstart_time;
        Cend_time = cend_time;
        Csite = csite;
        Cnum = cnum;
        Ctype = ctype;
        Climit = climit;
        Cremark = cremark;
        Tname = tname;
        this.TID = TID;
    }

    public String getCID()
    {
        return CID;
    }

    public void setCID(String CID)
    {
        this.CID = CID;
    }

    public String getCname()
    {
        return Cname;
    }

    public void setCname(String cname)
    {
        Cname = cname;
    }

    public Integer getCcredit()
    {
        return Ccredit;
    }

    public void setCcredit(Integer ccredit)
    {
        Ccredit = ccredit;
    }

    public String getCstart_time()
    {
        return Cstart_time;
    }

    public void setCstart_time(String cstart_time)
    {
        Cstart_time = cstart_time;
    }

    public String getCend_time()
    {
        return Cend_time;
    }

    public void setCend_time(String cend_time)
    {
        Cend_time = cend_time;
    }

    public String getCsite()
    {
        return Csite;
    }

    public void setCsite(String csite)
    {
        Csite = csite;
    }

    public Integer getCnum()
    {
        return Cnum;
    }

    public void setCnum(Integer cnum)
    {
        Cnum = cnum;
    }

    public Integer getCtype()
    {
        return Ctype;
    }

    public void setCtype(Integer ctype)
    {
        Ctype = ctype;
    }

    public Integer getClimit()
    {
        return Climit;
    }

    public void setClimit(Integer climit)
    {
        Climit = climit;
    }

    public String getCremark()
    {
        return Cremark;
    }

    public void setCremark(String cremark)
    {
        Cremark = cremark;
    }

    public String getTname()
    {
        return Tname;
    }

    public void setTname(String tname)
    {
        Tname = tname;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CID='" + CID + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Ccredit=" + Ccredit +
                ", Cstart_time='" + Cstart_time + '\'' +
                ", Cend_time='" + Cend_time + '\'' +
                ", Csite='" + Csite + '\'' +
                ", Cnum=" + Cnum +
                ", Ctype=" + Ctype +
                ", Climit=" + Climit +
                ", Cremark='" + Cremark + '\'' +
                ", Tname='" + Tname + '\'' +
                ", TID='" + TID + '\'' +
                '}';
    }
}
