

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.classes.*;


@WebServlet("/Login")
public class Login extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		EmpDB emp = new EmpDB();
		Employee employee = emp.Login(username, password);
			if (employee != null) {
				Cookie cookie_id = new Cookie("emp_id",username);
				Cookie cookie_pwd= new Cookie("pwd",password);
				response.addCookie(cookie_pwd);
				response.addCookie(cookie_id);
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
			else {
				response.sendRedirect("Login.jsp");

			}
}
		
}

