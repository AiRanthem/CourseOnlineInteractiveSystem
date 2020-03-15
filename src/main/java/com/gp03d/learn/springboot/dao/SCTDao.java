package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.SCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class SCTDao
{
    /**
     * @created by 李林禹 2019.7.9
     */

    @Autowired
    private static Connection con;
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://47.100.117.216:3306/GP03d?characterEncoding=utf-8";
    private static String user="test";
    private static String password="test";
    private static Statement statement;
    private static Statement statement1;
    private static Statement statement2;
    private ResultSet rs;
    private ResultSet rs1;
    private ResultSet rs2;

    static
    {
        try
        {
            Class.forName(driver);
            //getConnection()方法，连接Mysql数据库
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
            {
                //System.out.println("成功连接");
                statement = con.createStatement();
                statement1=con.createStatement();
                statement2=con.createStatement();
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public Collection<SCT> getAll()
    {
        Map<Integer,SCT> SCTs=new HashMap<Integer,SCT>();
        String sql="select * from SCT";

        try
        {
            Integer initID=1;
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                SCT sct = new SCT(rs.getString("SID"), rs.getString("CID"), rs.getString("TID"), rs.getInt("Grade"), rs.getString("Cname"), rs.getString("Tname"));
                SCTs.put(initID,sct);
                initID++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return SCTs.values();
    }

    public Collection<SCT> getAll(String SID)
    {
        Map<Integer,SCT> SCTs=new HashMap<Integer,SCT>();
        String sql="select * from SCT where SID = '"+SID+"'";

        try
        {
            Integer initID=1;
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                SCT sct = new SCT(rs.getString("SID"), rs.getString("CID"), rs.getString("TID"), rs.getInt("Grade"), rs.getString("Cname"), rs.getString("Tname"));
                SCTs.put(initID,sct);
                initID++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return SCTs.values();
    }

    public void chooseCourse(String sid,String cid,String tid)
    {
        String getTname = "select Cname,Tname from TC where CID = '"+cid+"'";
        try
        {
            rs1=statement.executeQuery(getTname);
            while(rs1.next())
            {
                String sql="insert into SCT(SID,CID,TID,Cname,Tname) values('"+sid+"','" +cid+"','"+tid+"'" +
                    ",'"+rs1.getString("Cname")+"','"+rs1.getString("Tname")+"')";
                String sql1="update Course set Cnum=Cnum+1 where CID='"+cid+"'";

                try
                {
                    statement1.execute(sql);
                    statement2.execute(sql1);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
