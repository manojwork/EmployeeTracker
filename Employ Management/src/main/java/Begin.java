

import java.io.IOException;

import com.classes.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Begin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookies [] = request.getCookies();
		String username="";
		String password="";
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("emp_id")) {
					
					username=c.getValue();
					
				}
				else if (c.getName().equals("pwd")) {
					password=c.getValue();
					
				}
			}
		}
		
		EmpDB emp = new EmpDB();
		Employee employee = emp.Login(username, password);
		
		if(employee == null) {
			response.sendRedirect("Login.jsp");

		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			if (employee.getAdmin()==1) {
				session.setAttribute("admin",true);

			}
			else {
				session.setAttribute("admin",null);
			}
			response.sendRedirect("EmployeeDashBoard.jsp");
		}
		
	}

}
