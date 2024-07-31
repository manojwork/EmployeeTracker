

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

@WebServlet("/DisplayEmp")
public class DisplayEmp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                EmpDB emp = new EmpDB();
                ArrayList<Employee> empList = emp.getEmp();
                
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Employees</title>");
                out.println("<style>");
                out.println("table { border-collapse: collapse; width: 100%; }");
                out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
                out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<table>");
                out.println("<tr><th>Employee id </th><th>Name</th><th>Role</th><th>Project</th><th>Tasks</th><th>Edit</th><th>Delete</th></tr>");

                for (Employee t : empList) {
                    out.println("<tr>");
                    out.println("<td>" + t.getEmp_id() + "</td>");
                    out.println("<td>" + t.getName() + "</td>");
                    out.println("<td>" + t.getRole() + "</td>");
                    out.println("<td>" + t.getProject() + "</td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='TaskEmp?empid=" + t.getEmp_id() + "'>Tasks</a></td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='EditEmployee?empid=" + t.getEmp_id() + "'>Edit</a></td>");
                    out.println("<td><a style='text-decoration:none;color:green;font-weight:500;' href='DEmp?empid=" + t.getEmp_id() + "'>Delete</a></td>");
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
