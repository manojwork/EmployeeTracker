

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Trigger")
public class Trigger extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String value = request.getParameter("option");
		
		if (value.equals("date")){
			response.sendRedirect("PieDate.jsp");
		}
		
		else if (value.equals("daily")) {
			
			response.sendRedirect("DailyChart");
		}
		else if(value.equals("weekly")) {
			response.sendRedirect("Weekly");

		}
		else {
			response.sendRedirect("Monthly");

		}
	
	}

}
