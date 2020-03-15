package com.gp03d.learn.springboot.entities;

//教师信息表
public class Teacher
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String TID;
    private String TPass;
    private String Tname;
    private String Tsex;
    private String Tcollege;
    private String Tdept;
    private String Ttitle;
    private String Tphone;
    private String Temail;
    private String Tdirection;

    public Teacher(String TID, String TPass, String tname, String tsex, String tcollege, String tdept, String ttitle, String tphone, String temail, String tdirection)
    {
        this.TID = TID;
        this.TPass = TPass;
        Tname = tname;
        Tsex = tsex;
        Tcollege = tcollege;
        Tdept = tdept;
        Ttitle = ttitle;
        Tphone = tphone;
        Temail = temail;
        Tdirection = tdirection;
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

    public void setTname(String tname)
    {
        Tname = tname;
    }

    public String getTsex()
    {
        return Tsex;
    }

    public void setTsex(String tsex)
    {
        Tsex = tsex;
    }

    public String getTcollege()
    {
        return Tcollege;
    }

    public void setTcollege(String tcollege)
    {
        Tcollege = tcollege;
    }

    public String getTdept()
    {
        return Tdept;
    }

    public void setTdept(String tdept)
    {
        Tdept = tdept;
    }

    public String getTtitle()
    {
        return Ttitle;
    }

    public void setTtitle(String ttitle)
    {
        Ttitle = ttitle;
    }

    public String getTphone()
    {
        return Tphone;
    }

    public void setTphone(String tphone)
    {
        Tphone = tphone;
    }

    public String getTemail()
    {
        return Temail;
    }

    public void setTemail(String temail)
    {
        Temail = temail;
    }

    public String getTdirection()
    {
        return Tdirection;
    }

    public void setTdirection(String tdirection)
    {
        Tdirection = tdirection;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "TID='" + TID + '\'' +
                ", TPass='" + TPass + '\'' +
                ", Tname='" + Tname + '\'' +
                ", Tsex='" + Tsex + '\'' +
                ", Tcollege='" + Tcollege + '\'' +
                ", Tdept='" + Tdept + '\'' +
                ", Ttitle='" + Ttitle + '\'' +
                ", Tphone='" + Tphone + '\'' +
                ", Temail='" + Temail + '\'' +
                ", Tdirection='" + Tdirection + '\'' +
                '}';
    }
}
