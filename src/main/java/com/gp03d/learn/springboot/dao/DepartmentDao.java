package com.gp03d.learn.springboot.dao;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.gp03d.learn.springboot.entities.Department;
import net.sf.resultsetmapper.ReflectionResultSetMapper;
import net.sf.resultsetmapper.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao
{
	/**
	 * @created by 李林禹 2019.7.10
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
			con= DriverManager.getConnection(url,user,password);
			if(!con.isClosed()) {
				//System.out.println("dept成功连接");
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
	
	public Collection<Department> getAll()
	{
		HashMap<String,Department> map=new HashMap<String,Department>();
		String sql="select * from Dept";
		try
		{
			rs=statement.executeQuery(sql);
			while(rs.next())
			{
				Department department=new Department(rs.getString("Dept"),rs.getString("Discipline"));
				map.put(rs.getString("Dept"),department);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return map.values();
	}

}
