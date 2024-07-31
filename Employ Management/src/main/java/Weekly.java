

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classes.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/Weekly")
public class Weekly extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
				HttpSession session = request.getSession();
				Employee employee = (Employee)session.getAttribute("employee");
				String empid= employee.getEmp_id();
				TaskDB taskDB= new TaskDB();
				ArrayList<String> dates =taskDB.getDates(empid);
				ArrayList<Integer> hours = new ArrayList<>();
				
				for (String s :dates) {
					int t=taskDB.getHours(empid,s);
					hours.add(t);
				}
		        Map<String, Integer> weeklyHours = new HashMap<>();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar calendar = Calendar.getInstance();

		        for (int i = 0; i < dates.size(); i++) {
		            Date date;
					try {
						date = dateFormat.parse(dates.get(i));
						calendar.setTime(date);
			            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			            String weekStart = dateFormat.format(calendar.getTime());
			            weeklyHours.put(weekStart, weeklyHours.getOrDefault(weekStart, 0) + hours.get(i));
					} catch (ParseException e) {
						e.printStackTrace();
					}
		            
		        }

		        ArrayList<String> weekStarts = new ArrayList<>(weeklyHours.keySet());
		        ArrayList<Integer> weeklyTotalHours = new ArrayList<>(weeklyHours.values());
		        
		        request.setAttribute("weekStarts", weekStarts);
		        request.setAttribute("weeklyTotalHours", weeklyTotalHours);

		        RequestDispatcher rd = request.getRequestDispatcher("Weekly.jsp");
				rd.forward(request, response);
	}


}
