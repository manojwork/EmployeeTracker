import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.classes.*;

@WebServlet("/DisplayTasks")
public class DisplayTasks extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee != null) {
            try {
                TaskDB tdb = new TaskDB();
                ArrayList<Task> tasks = tdb.getTasks(employee.getEmp_id());

                // Sort tasks by date in descending order
                sortTasksByDateDescending(tasks);

                PrintWriter out = response.getWriter();
                response.setContentType("text/html");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Transaction History</title>");
                out.println("<style>");
                out.println("table { border-collapse: collapse; width: 100%; }");
                out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
                out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<table>");
                out.println("<tr><th>Title</th><th>Date</th><th>Start Time</th><th>End Time</th><th>View</th><th>Edit</th><th>Delete</th></tr>");

                for (Task t : tasks) {
                    out.println("<tr>");
                    out.println("<td>" + t.getTitle() + "</td>");
                    out.println("<td>" + t.getDate() + "</td>");
                    out.println("<td>" + t.getTiming()[0] + "</td>");
                    out.println("<td>" + t.getTiming()[1] + "</td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='VTask?taskid=" + t.getTaskid() + "'>View</a></td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='ETask?taskid=" + t.getTaskid() + "'>Edit</a></td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='DTask?taskid=" + t.getTaskid() + "'>Delete</a></td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
