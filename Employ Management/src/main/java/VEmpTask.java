import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.classes.*;

@WebServlet("/VEmpTask")
public class VEmpTask extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("taskid"));
        String empid = request.getParameter("empid");
        TaskDB tdb = new TaskDB();
        Task task = null;
        task = tdb.getTask(empid, id);
        request.setAttribute("task", task);
        RequestDispatcher rd = request.getRequestDispatcher("viewTask.jsp");
        rd.forward(request, response);
    }
}
