

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

@WebServlet("/Monthly")
public class Monthly extends HttpServlet {

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
		        Map<String, Integer> monthlyHours = new HashMap<>();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
		        Calendar calendar = Calendar.getInstance();

		        for (int i = 0; i < dates.size(); i++) {
		            Date date;
					try {
						date = dateFormat.parse(dates.get(i));
						calendar.setTime(date);
			            calendar.set(Calendar.DAY_OF_MONTH, 1); // Set to the start of the month
			            String monthStart = monthFormat.format(calendar.getTime());

			            monthlyHours.put(monthStart, monthlyHours.getOrDefault(monthStart, 0) + hours.get(i));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		        }

		        // Prepare results
		        ArrayList<String> monthStarts = new ArrayList<>(monthlyHours.keySet());
		        ArrayList<Integer> monthlyTotalHours = new ArrayList<>(monthlyHours.values());

		        // Sort results by month start date
		        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(monthlyHours.entrySet());
		        entryList.sort(Map.Entry.comparingByKey());
		        
		        monthStarts.clear();
		        monthlyTotalHours.clear();
		        for (Map.Entry<String, Integer> entry : entryList) {
		            monthStarts.add(entry.getKey());
		            monthlyTotalHours.add(entry.getValue());
		        }
		        request.setAttribute("monthStarts", monthStarts);
		        request.setAttribute("monthlyTotalHours", monthlyTotalHours);

		        RequestDispatcher rd = request.getRequestDispatcher("Monthly.jsp");
				rd.forward(request, response);
	
	}

}
