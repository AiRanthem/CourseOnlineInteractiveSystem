package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.TeacherUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TUserDao
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

    public Collection<TeacherUser> getAll()
    {
        String sql="select * from Teacher";
        Map<String, TeacherUser> TUs = new HashMap<String, TeacherUser>();
        try
        {
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                TeacherUser teacherUser=new TeacherUser(rs.getString("TID"),rs.getString("TPass"),rs.getString("Tname"));
                TUs.put(rs.getString("TID"),teacherUser);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return TUs.values();
    }

    public void addUser(String tid, String tpass)
    {
        String sql1="insert into Teacher values(TID='"+tid+"',TPass='"+tpass+"')";
        String sql2="update Teacher set STstatus=1 where STID='"+tid+"'";
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
