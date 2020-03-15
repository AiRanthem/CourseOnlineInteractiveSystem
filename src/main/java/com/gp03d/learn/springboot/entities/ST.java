package com.gp03d.learn.springboot.entities;

//注册用的ID列表
public class ST
{
    /**
     * @Created by 李林禹 2019.7.9
     */
    private String STID;
    private Integer STstatus;

    public ST(String STID, Integer STstatus)
    {
        this.STID = STID;
        this.STstatus = STstatus;
    }

    public String getSTID()
    {
        return STID;
    }

    public void setSTID(String STID)
    {
        this.STID = STID;
    }

    public Integer getSTstatus()
    {
        return STstatus;
    }

    public void setSTstatus(Integer STstatus)
    {
        this.STstatus = STstatus;
    }

    @Override
    public String toString()
    {
        return "ST{" +
                "STID='" + STID + '\'' +
                ", STstatus=" + STstatus +
                '}';
    }
}
