package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.StudentUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Repository
public class SUserDao
{
    /**
     * @created by 李林禹 2019.7.9
     */

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

    public Collection<StudentUser> getAll()
    {
        String sql="select * from Student";
        Map<String, StudentUser> SUs = new HashMap<String, StudentUser>();
        try
        {
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                StudentUser studentUser=new StudentUser(rs.getString("Spass"),rs.getString("SID"),rs.getString("Sname"));
                SUs.put(rs.getString("SID"),studentUser);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return SUs.values();
    }

    public void addUser(String sid, String spass)
    {
        String sql1="insert into Student(SID,Spass) values('"+sid+"','"+spass+"')";
        String sql2="update ST set STstatus=1 where STID='"+sid+"'";
        try
        {
            statement.execute(sql1);
            statement.execute(sql2);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(String sid)
    {
        String sql1="delete from Student where SID='"+sid+"'";
        String sql2="update ST set STstatus=0 where STID='"+sid+"'";
        try
        {
            statement.execute(sql1);
            statement.execute(sql2);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
