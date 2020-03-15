package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.GroupCourse;
import com.gp03d.learn.springboot.entities.SCT;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GroupCourseDao {
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

    // 根据传入的CID获得相应的元组
    public Collection<GroupCourse> getAll(String CID){
        HashMap<Integer, GroupCourse> map = new HashMap<>();

        String sql="select * from GroupS where CID = '"+CID+"'";

        try
        {
            Integer initID=1;
            rs=statement.executeQuery(sql);
            while (rs.next())
            {
                GroupCourse group = new GroupCourse(rs.getString("GID"), rs.getString("CID"), rs.getString("Gname"));
                map.put(initID,group);
                initID++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return map.values();
    }

}
