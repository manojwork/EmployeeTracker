

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.classes.*;


@WebServlet("/TaskEmp")
public class TaskEmp extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	HttpSession session = request.getSession();
	        Employee employee = (Employee) session.getAttribute("employee");
	        String admin=String.valueOf( session.getAttribute("admin"));
	        if (employee != null && admin != null) {
	            
	            	String empid = request.getParameter("empid");
	                TaskDB tdb = new TaskDB();
	                ArrayList<Task> tasks = tdb.getTasks(empid);
	                sortTasksByDateDescending(tasks);
	                
	                request.setAttribute("tasks",tasks);
	                
	                RequestDispatcher  rd = request.getRequestDispatcher("DisplayEmpTask.jsp");
	                rd.forward(request, response);
	                
	            
	        }
	    }

	    private void sortTasksByDateDescending(ArrayList<Task> tasks) {
	        // Define a comparator for the Task class to sort in descending order
	        Comparator<Task> dateComparator = new Comparator<Task>() {
	            @Override
	            public int compare(Task t1, Task t2) {
	                return t2.getDate().compareTo(t1.getDate());
	            }
	        };

	        // Sort the tasks using the comparator
	        Collections.sort(tasks, dateComparator);
	    }
	}
