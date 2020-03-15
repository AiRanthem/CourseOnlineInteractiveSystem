package com.gp03d.learn.springboot.dao;

import com.gp03d.learn.springboot.entities.Teacher;
import net.sf.resultsetmapper.ReflectionResultSetMapper;
import net.sf.resultsetmapper.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Repository
public class TeacherDao
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

    public void save(Teacher teacher)
    {
        String sql="Insert into Teacher Values('"+teacher.getTID()+"','"+teacher.getTPass()+"','"+teacher.getTname()+"','"+teacher.getTsex()+"','"+teacher.getTcollege()+"','"+teacher.getTdept()+"','"+teacher.getTtitle()+"','"+teacher.getTphone()+"','"+teacher.getTemail()+"','"+teacher.getTdirection()+"')";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //查询所有老师
    public Collection<Teacher> getAll()
    {
        HashMap<String,Teacher> map=new HashMap<String, Teacher>();
        String sql="select * from Teacher";
        try
        {
            rs=statement.executeQuery(sql);
            while(rs.next())
            {
                Teacher teacher=new Teacher(rs.getString("TID"),rs.getString("TPass"),rs.getString("Tname"),rs.getString("Tsex"),rs.getString("Tcollege"),rs.getString("Tdept"),rs.getString("Ttitle"),rs.getString("Tphone"),rs.getString("Temail"),rs.getString("Tdirection"));
                map.put(rs.getString("TID"),teacher);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return map.values();
    }

    public Teacher get(String tid)
    {
        String sql="select * from Teacher where TID='"+tid+"'";
        try
        {
            rs=statement.executeQuery(sql);
            while(rs.next())
            {
                Teacher teacher=new Teacher(rs.getString("TID"),rs.getString("TPass"),rs.getString("Tname"),rs.getString("Tsex"),rs.getString("Tcollege"),rs.getString("Tdept"),rs.getString("Ttitle"),rs.getString("Tphone"),rs.getString("Temail"),rs.getString("Tdirection"));
                return teacher;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String tid)
    {
        String sql="delete from Teacher where TID='"+tid+"'";
        try
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean has(String tid)
    {
        String sql="select * from Teacher where TID='"+tid+"'";
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
