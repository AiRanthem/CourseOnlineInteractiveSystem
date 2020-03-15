package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.Student;
import net.sf.resultsetmapper.ReflectionResultSetMapper;
import net.sf.resultsetmapper.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Repository
public class StudentDao
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

    public void update(HttpServletRequest request)
    {
        String sql="update Student set Sname='"+request.getParameter("Sname")+"'" +
                ",Ssex='"+request.getParameter("Ssex")+"'" +
                ",Sphone='"+request.getParameter("Sphone")+"'" +
                ",Semail='"+request.getParameter("Semail")+"'" +
                ",Scollege='"+request.getParameter("department")+"'" +
                ",Sdept='"+request.getParameter("Sdept")+"'" +
                ",Sgrade='"+request.getParameter("Sgrade")+"'" +
                ",Sstatus='"+request.getParameter("Sstatus")+"'" +
                ",Sbirthplace='"+request.getParameter("Sbirthplace")+"' " +
                "where SID='"+request.getParameter("SID")+"'";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //查询所有学生
    public Collection<Student> getAll()
    {
        HashMap<String,Student> map=new HashMap<String, Student>();
        String sql="select * from Student";
        try
        {
            rs=statement.executeQuery(sql);
            while(rs.next())
            {
                Student student=new Student(rs.getString("SID"),rs.getString("SPass"),rs.getString("Sname"),rs.getString("Ssex"),rs.getString("Sphone"),rs.getString("Semail"),rs.getString("Scollege"),rs.getString("Sdept"),rs.getInt("Sgrade"),rs.getString("Sstatus"),rs.getString("Sbirthplace"));
                map.put(rs.getString("SID"),student);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return map.values();
    }

    public Student get(String sid)
    {
        String sql="select * from Student where SID='"+sid+"'";
        try
        {
            rs=statement.executeQuery(sql);
            while(rs.next())
            {
                Student student=new Student(rs.getString("SID"),rs.getString("SPass"),rs.getString("Sname"),rs.getString("Ssex"),rs.getString("Sphone"),rs.getString("Semail"),rs.getString("Scollege"),rs.getString("Sdept"),rs.getInt("Sgrade"),rs.getString("Sstatus"),rs.getString("Sbirthplace"));
                return student;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String sid)
    {
        String sql="delete from Student where SID='"+sid+"'";
        String sql2="update ST set STstatus=0 where STID='"+sid+"'";
        try
        {
            statement.execute(sql);
            statement.execute(sql2);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean has(String sid)
    {
        String sql="select * from Student where SID='"+sid+"'";
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
