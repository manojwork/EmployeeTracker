import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.classes.*;

@WebServlet("/VTask")
public class VTask extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("taskid"));
        } catch (NumberFormatException e) {
            response.sendRedirect("begin");
            return;
        }

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        // Check if the employee session attribute is null
        if (employee == null) {
            response.sendRedirect("begin");
            return;
        }

        // Fetch the task from TaskDB
        TaskDB tdb = new TaskDB();
        Task task = null;
        try {
            task = tdb.getTask(employee.getEmp_id(), id);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        // Check if the task is null
        if (task == null) {
            response.sendRedirect("begin");
            return;
        }
        

        // Set the task attribute and forward to JSP
        request.setAttribute("task", task);
        RequestDispatcher rd = request.getRequestDispatcher("viewTask.jsp");
        rd.forward(request, response);
    }
}
