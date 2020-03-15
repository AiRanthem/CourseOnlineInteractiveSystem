package com.gp03d.learn.springboot.dao;


import com.gp03d.learn.springboot.entities.GroupCourse;
import com.gp03d.learn.springboot.entities.GroupMsg;
import com.gp03d.learn.springboot.entities.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GroupMsgDao {
    private static Connection con;
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://47.100.117.216:3306/GP03d?characterEncoding=utf-8";
    private static String user="test";
    private static String password="test";
    private static Statement statement;
    private static Statement statement1;
    private ResultSet rs;
    private ResultSet rs1;

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
                statement1 = con.createStatement();
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

    // 根据传入的GID获得相应的Msg
    public Collection<Msg> getAll(String GID){
        HashMap<Integer, Msg> map = new HashMap<>();

        String sql="select * from Messages where GID = '"+GID+"'";

        try
        {
            Integer initID=1;
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                Msg msg = new Msg(rs.getString("MID"), rs.getString("UID"), rs.getString("Uname"), rs.getString("Text"), rs.getString("Mtime"));
                map.put(initID,msg);
                initID++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return map.values();}

    public void save(String UID, String Uname, String Text, String GID)
    {
        String sql="insert into Messages(UID,Uname,Text,GID) values('"+UID+"','"+Uname+"','"+Text+"','"+GID+"')";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
