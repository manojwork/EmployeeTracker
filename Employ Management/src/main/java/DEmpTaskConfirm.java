import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DEmpTaskConfirm")
public class DEmpTaskConfirm extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskid"));
        String empid = request.getParameter("empid");

        TaskDB tdb = new TaskDB();
        boolean success = tdb.deleteTask(taskId);
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
        out.println("<title>Delete Task</title></head><body>");
        out.println("<script>");

        if (success) {
            out.println("Swal.fire({");
            out.println("  title: 'Deleted!',");
            out.println("  text: 'Your task has been deleted.',");
            out.println("  icon: 'success'");
            out.println("}).then(() => { window.location.href = 'TaskEmp?empid="+empid+"'; });");
        } else {
            out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'There was an error deleting your task.',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });");
        }

        out.println("</script>");
        out.println("</body></html>");
        out.close();
    }
}
