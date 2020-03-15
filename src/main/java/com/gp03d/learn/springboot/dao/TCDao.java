package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.Course;
import com.gp03d.learn.springboot.entities.TC;
import com.gp03d.learn.springboot.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TCDao
{
    /**
     * @created by 李林禹 2019.7.11
     */

    @Autowired
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
            con= DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
            {
                //System.out.println("成功连接");
                statement=con.createStatement();
                statement1=con.createStatement();
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

    private static Integer initId_two = 11200;

    public void save(Course course, Teacher teacher)
    {
        String CID="C"+initId_two.toString();
        if(course.getCID() == null)
        {
            course.setCID(CID);
            initId_two++;
        }
        //数据库实现
        String sql1="insert into TC Values('"+course.getCID()+"','"+course.getCname()+"','"+teacher.getTID()+"','"+teacher.getTname()+"')";
        String sql="Insert into Course Values('"+CID+"','"+course.getCname()+"','"+course.getCcredit()+"','"+course.getCstart_time()+"','"+course.getCend_time()+"','"+course.getCsite()+"','"+course.getCtype()+"','"+course.getClimit()+"','"+course.getCremark()+"'";
        try
        {
            statement.execute(sql);
            statement1.execute(sql1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //查询所有课程
    public Collection<Course> getAll(String TID)
    {
        HashMap<String, Course> map = new HashMap<String, Course>();
        //数据库实现
        String sql="select * from TC where TID='"+TID+"'";
        try
        {
            rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String sql1 = "select * from Course where CID='" + rs.getString("CID") + "'";
                try
                {
                    rs1 = statement1.executeQuery(sql1);
                    while(rs1.next())
                    {
                        Course course = new Course(rs1.getString("CID"), rs1.getString("Cname"), rs1.getInt("Ccredit"), rs1.getString("Cstart_time"), rs1.getString("Cend_time"), rs1.getString("Csite"), rs1.getInt("Cnum"), rs1.getInt("Ctype"), rs1.getInt("Climit"), rs1.getString("Cremark"), rs.getString("Tname"),rs.getString("TID"));
                        map.put(rs1.getString("CID"), course);
                    }
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
        return map.values();
    }

    public void delete(String cid)
    {
        String sql="delete from TC where CID='"+cid+"'";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void uodate(Course course)
    {
        String sql="update Course set Cname='"+course.getCname()+"'" +
                ",Ccredit='"+course.getCcredit()+"'" +
                ",Cstart_time='"+course.getCstart_time()+"'" +
                ",Cend_time='"+course.getCend_time()+"'" +
                ",Csite='"+course.getCsite()+"'" +
                ",Ctype='"+course.getCtype()+"'" +
                ",Climit='"+course.getClimit()+"'" +
                ",Cremark='"+course.getCremark()+"'" +
                " where CID='"+course.getCID()+"'";
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
