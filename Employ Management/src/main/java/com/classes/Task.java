package com.classes;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Task extends HttpServlet {
	
	private int task_id;
	private String emp_id;
	private String date;
	private String title;
	private int total;
	private float [] timing;
	private String description;
	
	public Task(int task_id,String emp_id,String date,String title,int total,float [] timing,String description) {
		this.task_id=task_id;
		this.emp_id=emp_id;
		this.date =date;
		this.title=title;
		this.timing=timing;
		this.description = description;
		this.total=total;
	}
	
	public int getTaskid() {
		return this.task_id;
	}
	
	public String getEmpid() {
		return this.emp_id;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public float[] getTiming() {
		return this.timing;
	}
	

	
	public int getTotal() {

		return this.total;
	
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
