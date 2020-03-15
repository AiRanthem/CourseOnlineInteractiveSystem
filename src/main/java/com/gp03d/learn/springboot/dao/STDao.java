package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.ST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class STDao
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
    private ResultSet rs;

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

    public Collection<ST> getAllStudent()
    {
        String sql="select * from ST where STstatus=0 AND STID LIKE 'S%'";
        Map<String,ST> Ss=new HashMap<String, ST>();
        try
        {
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                ST st=new ST(rs.getString("STID"),rs.getInt("STstatus"));
                Ss.put(rs.getString("STID"),st);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Ss.values();
    }

    public Collection<ST> getAllTeacher()
    {
        String sql="select * from ST where STstatus=0 AND STID LIKE 'T%'";
        Map<String,ST> Ts=new HashMap<String, ST>();
        try
        {
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                ST st=new ST(rs.getString("STID"),rs.getInt("STstatus"));
                Ts.put(rs.getString("STID"),st);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Ts.values();
    }
}
