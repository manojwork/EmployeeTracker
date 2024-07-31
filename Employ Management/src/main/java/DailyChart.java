

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classes.Employee;

/**
 * Servlet implementation class DailyChart
 */
@WebServlet("/DailyChart")
public class DailyChart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TaskDB taskDB = new TaskDB();
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		String empid=employee.getEmp_id();
		ArrayList<String> dates =taskDB.getDates(empid);
		ArrayList<Integer> hours = new ArrayList<>();
		
		for (String s :dates) {
			int t=taskDB.getHours(empid,s);
			hours.add(t);
		}
		
		request.setAttribute("dates", dates);
		request.setAttribute("hours", hours);
		System.out.println(dates.size());
		System.out.println(hours.get(1));

		RequestDispatcher rd = request.getRequestDispatcher("DailyChart.jsp");
		rd.forward(request, response);
	
	}


}
