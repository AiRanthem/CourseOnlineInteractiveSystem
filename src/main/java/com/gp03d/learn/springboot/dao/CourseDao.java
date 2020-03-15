package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.Course;
import net.sf.resultsetmapper.ReflectionResultSetMapper;
import net.sf.resultsetmapper.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseDao
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

    private static Integer initId = 1100;

//    public void save(Course course)
//    {
//        String CID="C"+initId.toString();
//        if(course.getCID() == null)
//        {
//            course.setCID(CID);
//            initId++;
//        }
//        //数据库实现
//        String sql="Insert into Course Values('"+CID+"','"+course.getCname()+"','"+course.getCcredit()+"','"+course.getCstart_time()+"','"+course.getCend_time()+"','"+course.getCsite()+"','"+course.getCtype()+"','"+course.getClimit()+"','"+course.getCremark()+"'";
//        try
//        {
//            statement.execute(sql);
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }

    //查询所有课程
    public Map<String,Course> getAll()
    {
        HashMap<String, Course> map = new HashMap<String, Course>();
        //数据库实现
        String sql="select * from Course";
        try
        {
            rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String sql1 = "select * from TC where CID='" + rs.getString("CID") + "'";
                try
                {
                    rs1 = statement1.executeQuery(sql1);
                    while(rs1.next())
                    {
                        Course course = new Course(rs.getString("CID"), rs.getString("Cname"), rs.getInt("Ccredit"), rs.getString("Cstart_time"), rs.getString("Cend_time"), rs.getString("Csite"), rs.getInt("Cnum"), rs.getInt("Ctype"), rs.getInt("Climit"), rs.getString("Cremark"), rs1.getString("Tname"),rs1.getString("TID"));
                        map.put(rs.getString("CID"), course);
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
        return map;
    }

    public Course get(String cid)
    {
        String sql="select * from Course where CID='"+cid+"'";
        try
        {
            rs=statement.executeQuery(sql);

            while (rs.next())
            {
                String sql1 = "select * from TC where CID='" + rs.getString("CID") + "'";
                try
                {
                    rs1 = statement1.executeQuery(sql1);
                    while(rs1.next())
                    {
                        Course course = new Course(rs.getString("CID"), rs.getString("Cname"), rs.getInt("Ccredit"), rs.getString("Cstart_time"), rs.getString("Cend_time"), rs.getString("Csite"), rs.getInt("Cnum"), rs.getInt("Ctype"), rs.getInt("Climit"), rs.getString("Cremark"), rs1.getString("Tname"),rs1.getString("TID"));
                        return course;
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
        return null;
    }

    public void delete(String cid)
    {
        String sql="delete from Course where CID='"+cid+"'";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setLimit(String cid, Integer climit)
    {
        String sql="update Course set Climit='"+climit+"' where CID='"+cid+"'";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean has(String cid)
    {
        String sql="select * from Course where CID='"+cid+"'";
        try
        {
            rs=statement.executeQuery(sql);
            if(rs!=null)
                return true;
            else
                return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
