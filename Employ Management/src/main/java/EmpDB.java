import java.io.IOException;
import com.classes.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
public class EmpDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BankingSystem@12";
    private Connection con;

    public EmpDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return this.con;
    }

    public Employee Login(String emp_id, String password) {
    		Employee temp = null;
    		
        try {
            PreparedStatement s = getCon().prepareStatement("SELECT * FROM emp WHERE emp_id = ? AND password = ?");
            s.setString(1, emp_id);
            s.setString(2, password);
            ResultSet rs = s.executeQuery();
            if (rs.next()) 
            {
				String id = rs.getString("emp_id");
				String name = rs.getString("name");
				String pwd = rs.getString("password");
				String role=rs.getString("role");
				String project = rs.getString("project");
				int admin = rs.getInt("admin");
				
				Employee employee = new Employee(id,name,pwd,role,project,admin);
				return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return temp;
    }
    
    public boolean updatePassword(String empid ,String oldPassword,String newPassword) {
		String query="update emp set password = ? where emp_id= ? and password = ?;";
		boolean temp=false;
		try {
			PreparedStatement q = this.con.prepareStatement(query);
			q.setString(1,newPassword);
			q.setString(2,empid);
			q.setString(3,oldPassword);
			int row = q.executeUpdate();
			if (row>0) {
				temp=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
		
    }
    
    public ArrayList<Employee> getEmp()  {
        ArrayList<Employee> empList = new ArrayList<>();
        String query = "SELECT * FROM emp WHERE admin = 0";

        try { PreparedStatement s = this.con.prepareStatement(query);
             ResultSet rs = s.executeQuery() ;
            while (rs.next()) {
                String id = rs.getString("emp_id");
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                String role = rs.getString("role");
                String project = rs.getString("project");
                int admin = rs.getInt("admin");

                Employee employee = new Employee(id, name, pwd, role, project, admin);
                empList.add(employee);
            }
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }

        return empList;
    }
    
    public int getMaxID() {
    	int id=0;
    	try {
			PreparedStatement s =this.con.prepareStatement("select max(id) as id from emp;");
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
			id=	rs.getInt("id");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return id;
    	
    }
    
    public boolean addEmployee(String empid,String name,String role,String project,String password) {
    	boolean b=false;
    	
    	
    	
    	try {
			PreparedStatement  s = this.con.prepareStatement("insert into emp (emp_id,name,role,project,password,admin) values(?,?,?,?,?,?);");
			s.setString(1,empid);
			s.setString(2,name);
			s.setString(3,role);
			s.setString(4,project);
			s.setString(5,password);
			s.setInt(6,0);
			int row =s.executeUpdate();
			
			if (row>0) {
				b=true;
			}

    	
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return b;
    }
    
    public Employee getEmployee(String empid) {
    	Employee employee=null;
    	ArrayList<Employee> empList = getEmp();
    	
    	for (Employee e:empList) {
    		
    		if (e.getEmp_id().endsWith(empid)) {
    			employee=e;
    			break;
    		}
    		
    	}
    	return employee;
    	
    }
    
     public boolean updateEmployee(String empid,String name,String role,String project,String password) {
    	 boolean b=false;
    	 
    	 try {
			PreparedStatement p = this.con.prepareStatement("update emp set name= ?,role =?,project=?,password=? where emp_id= ?;");
			p.setString(1, name);
			p.setString(2, role);
			p.setString(3, project);
			p.setString(4, password);
			p.setString(5, empid);
			int row =p.executeUpdate();
			if(row>0) {
				b=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	 return b;
     }
    public boolean  deleteEmployee(String empid) {
    	boolean b= false;
    	
    	try {
			PreparedStatement p= this.con.prepareStatement("delete from emp where emp_id=? ");
			p.setString(1,empid);
			int row =p.executeUpdate();
			if (row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return b;
    	
    }
	
}
