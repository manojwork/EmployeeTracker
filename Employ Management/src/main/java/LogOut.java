

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogOut")
public class LogOut extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		if (session.getAttribute("employee") != null) {
			session.removeAttribute("employee");
			
		}
		
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");

		}
		

		Cookie c1 = new Cookie("emp_id","");
		Cookie c2 = new Cookie("pwd","");
		response.addCookie(c1);
		response.addCookie(c2);
		response.sendRedirect("Login.jsp");
		
	}

}
