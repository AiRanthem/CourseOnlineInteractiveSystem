package com.gp03d.learn.springboot.entities;

//学生信息表
public class Student
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String SID;
    private String Spass;
    private String Sname;
    private String Ssex;
    private String Sphone;
    private String Semail;
    private String Scollege;
    private String Sdept;
    private Integer Sgrade;
    private String Sstatus;
    private String Sbirthplace;

    public Student(String SID,String Spass){
        this.SID=SID;
        this.Spass=Spass;
    }

    public Student(String SID, String spass, String sname, String ssex, String sphone, String semail, String scollege, String sdept, Integer sgrade, String sstatus, String sbirthplace)
    {
        this.SID = SID;
        Spass = spass;
        Sname = sname;
        Ssex = ssex;
        Sphone = sphone;
        Semail = semail;
        Scollege = scollege;
        Sdept = sdept;
        Sgrade = sgrade;
        Sstatus = sstatus;
        Sbirthplace = sbirthplace;
    }

    public String getSID()
    {
        return SID;
    }

    public void setSID(String SID)
    {
        this.SID = SID;
    }

    public String getSpass()
    {
        return Spass;
    }

    public void setSpass(String spass)
    {
        Spass = spass;
    }

    public String getSname()
    {
        return Sname;
    }

    public void setSname(String sname)
    {
        Sname = sname;
    }

    public String getSsex()
    {
        return Ssex;
    }

    public void setSsex(String ssex)
    {
        Ssex = ssex;
    }

    public String getSphone()
    {
        return Sphone;
    }

    public void setSphone(String sphone)
    {
        Sphone = sphone;
    }

    public String getSemail()
    {
        return Semail;
    }

    public void setSemail(String semail)
    {
        Semail = semail;
    }

    public String getScollege()
    {
        return Scollege;
    }

    public void setScollege(String scollege)
    {
        Scollege = scollege;
    }

    public String getSdept()
    {
        return Sdept;
    }

    public void setSdept(String sdept)
    {
        Sdept = sdept;
    }

    public Integer getSgrade()
    {
        return Sgrade;
    }

    public void setSgrade(Integer sgrade)
    {
        Sgrade = sgrade;
    }

    public String getSstatus()
    {
        return Sstatus;
    }

    public void setSstatus(String sstatus)
    {
        Sstatus = sstatus;
    }

    public String getSbirthplace()
    {
        return Sbirthplace;
    }

    public void setSbirthplace(String sbirthplace)
    {
        Sbirthplace = sbirthplace;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "SID='" + SID + '\'' +
                ", Spass='" + Spass + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sphone='" + Sphone + '\'' +
                ", Semail='" + Semail + '\'' +
                ", Scollege='" + Scollege + '\'' +
                ", Sdept='" + Sdept + '\'' +
                ", Sgrade='" + Sgrade + '\'' +
                ", Sstatus='" + Sstatus + '\'' +
                ", Sbirthplace='" + Sbirthplace + '\'' +
                '}';
    }
}
