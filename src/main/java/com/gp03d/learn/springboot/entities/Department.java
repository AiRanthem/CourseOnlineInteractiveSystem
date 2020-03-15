package com.gp03d.learn.springboot.entities;

//学院
public class Department
{
	/**
	 * @created by 李林禹 2019.7.9
	 */
	private String Dept;
	private String Discipline;

	public Department(String dept, String discipline)
	{
		this.Dept = dept;
		this.Discipline = discipline;
	}

	public String getDept()
	{
		return Dept;
	}

	public void setDept(String dept)
	{
		Dept = dept;
	}

	public String getDiscipline()
	{
		return Discipline;
	}

	public void setDiscipline(String discipline)
	{
		Discipline = discipline;
	}

	@Override
	public String toString()
	{
		return "Department{" +
				"Dept='" + Dept + '\'' +
				", Discipline='" + Discipline + '\'' +
				'}';
	}
}
