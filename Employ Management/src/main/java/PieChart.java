

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
 * Servlet implementation class PieChart
 */
@WebServlet("/PieChart")
public class PieChart extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		TaskDB taskDB= new TaskDB();
		HttpSession session= request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		ArrayList<String> labels=taskDB.getLabels(date,employee.getEmp_id());
		ArrayList<Integer> hours=taskDB.getTime(date,employee.getEmp_id());
		request.setAttribute("labels", labels);
		request.setAttribute("hours",hours );
		System.out.println(labels.size());
		System.out.println(hours.size());
		RequestDispatcher rd = request.getRequestDispatcher("PieChart.jsp");
		rd.forward(request, response);
	}

}
