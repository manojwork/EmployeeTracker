import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.classes.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TaskDB")
public class TaskDB extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BankingSystem@12";

    private Connection con;

    public TaskDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasks(String emp_id) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {        
        	 PreparedStatement s = this.con.prepareStatement("SELECT * FROM task WHERE emp_id = ?;");
             s.setString(1, emp_id);
             ResultSet rs = s.executeQuery();
             while (rs.next()) {
                 int taskId = rs.getInt("task_id");
                 String empId = rs.getString("emp_id");
                 String date = rs.getString("date");
                 String title = rs.getString("title");
                 int total = rs.getInt("total");
                 float start = rs.getFloat("start");
                 float end = rs.getFloat("end");
                 String description = rs.getString("description");
                 tasks.add(new Task(taskId, empId, date, title, total, new float[]{start, end}, description));
             }
         } catch (SQLException e) {
     		e.printStackTrace();
         }

         return tasks;
     }
    
   public int getHours(String empid,String date) {
	   
	   ArrayList <Task> tasks = getTasks(empid);
	   int total=0;
	   for (Task t : tasks) {
		   
		   if (t.getDate().equals(date)) {
			   total=total+t.getTotal();
		   }
	   }
	   return total;
   }
   
   public Task getTask(String empid,int id) {
	   
	   ArrayList <Task> tasks = getTasks(empid);
	   Task total = null;
	   for (Task t : tasks) {
		   
		   if (t.getTaskid() == id) {
			   total= t;
		   }
		   
	   }
	   
	   return total;
   }
   
   public boolean checkVacancy(String empid,float start,float end,String d) {
	   ArrayList<Task> tasks = getTasks(empid);
	   ArrayList<float[]> timing = new ArrayList<>();
	   for (Task t : tasks) {
		   if (t.getDate().equals(d)) {
			   timing.add(t.getTiming());
		   }	
	   }
	   
	   return trackTime(start,end,timing);
	   
		 
	   }
   
   public boolean checkVForUpdate(String empid,float start,float end,String d,int id) {
	   ArrayList<Task> tasks = getTasks(empid);
	   ArrayList<float[]> timing = new ArrayList<>();
	   for (Task t : tasks) {
		   if (t.getDate().equals(d) && t.getTaskid()  != id ) {
			   timing.add(t.getTiming());
		   }	
	   }
	   
	   return trackTime(start,end,timing);
	   
		 
	   }
   
   public boolean trackTime(float start,float end ,ArrayList<float[]> timing ) {
	   boolean temp=true;
	   for (float[] arr: timing) {
		   
		   if (arr[0]<=start && arr[1]>=end) {
			   temp=false;
		   }
		   else if (arr[0]>=start && arr[0]<=end) {
			   temp=false;
		   }
		   else if (arr[1]<=end && arr[1]>=start) {
			   temp=false;
		   }
		   else if (arr[0]>=start && arr[1]<=end) {
			   temp=false;
		   }
	   }
	   
	   return temp;
   }
   
   public boolean addTask(String title,String description,float startTime,float endTime,int total,String date,String empid) {
	   
	  String Query= "INSERT INTO task (date,start,end,title,description,total,emp_id) VALUES(?,?,?,?,?,?,?);";
	  
	  
	  try {
		PreparedStatement s = this.con.prepareStatement(Query);
		s.setString(1,date);
		s.setFloat(2,startTime);
		s.setFloat(3,endTime);
		s.setString(4,title);
		s.setString(5,description);
		s.setInt(6,total);
		s.setString(7,empid);
		int row = s.executeUpdate();
		if (row>0) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return false;
   
   }
   
   public boolean updateTask(int id ,String title,String description,float startTime,float endTime,int total,String date) {
	   
	   String Query = "UPDATE task SET date = ?, start = ?, end = ?, title = ?, description = ?, total = ? WHERE task_id = ?;";
		  
		  
		  try {
			PreparedStatement s1 = this.con.prepareStatement(Query);
			s1.setString(1,date);
			s1.setFloat(2,startTime);
			s1.setFloat(3,endTime);
			s1.setString(4,title);
			s1.setString(5,description);
			s1.setInt(6,total);
			s1.setInt(7,id);
			int row = s1.executeUpdate();
	        
			if (row>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		  
	   
	   }
   
   public boolean deleteTask(int id) {
	   
	   boolean temp=false;
	   
	   String query="delete from task where task_id = ?;";
	   
	   try {
		PreparedStatement s = this.con.prepareStatement(query);
		s.setInt(1, id);
		int row = s.executeUpdate();
		if (row>0) {
			temp=true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return temp;
	   
	   
   }
   
   
   
   
   public ArrayList<String> getLabels(String date,String empid){
	   ArrayList<String> labels = new ArrayList<>();
	   ArrayList <Task> tasks = getTasks(empid);
	   for (Task t : tasks) {
		   
		   if (t.getDate().equals(date)) {
			   labels.add(t.getTitle());
		   }
		   
	   }
	   
	   return labels;
   }
  
   public ArrayList<Integer> getTime(String date,String empid){
	   ArrayList<Integer> labels = new ArrayList<>();
	   ArrayList <Task> tasks = getTasks(empid);
	   for (Task t : tasks) {
		   
		   if (t.getDate().equals(date)) {
			   labels.add(t.getTotal());
		   }
		   
	   }
	   
	   return labels;
   }


   public ArrayList<String> getDates(String empid){
	   ArrayList<String> dates = new ArrayList<>();
	   String query ="select distinct date from task where emp_id = ?;";
	   
	   try {
		PreparedStatement s = this.con.prepareStatement(query);
		s.setString(1, empid);
		ResultSet rs = s.executeQuery();
		while (rs.next()) {
			String date =rs.getString("date");
			dates.add(date);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return dates;
	   
   }

   
   }
