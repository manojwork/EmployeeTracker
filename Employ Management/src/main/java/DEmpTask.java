import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DEmpTask")
public class DEmpTask extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskid"));
        String empid = request.getParameter("empid");
        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
        out.println("<title>Delete Task</title></head><body>");
        out.println("<script>");
        out.println("Swal.fire({");
        out.println("  title: 'Are you sure?',");
        out.println("  text: 'Do you want to delete this task?',");
        out.println("  icon: 'warning',");
        out.println("  showCancelButton: true,");
        out.println("  confirmButtonText: 'Yes, delete it!',");
        out.println("  cancelButtonText: 'No, keep it'");
        out.println("}).then((result) => {");
        out.println("  if (result.isConfirmed) {");
        out.println("    window.location.href = 'DEmpTaskConfirm?taskid=" + taskId + "&empid="+empid+"';");
        out.println("  } else {");
        out.println("    window.location.href = 'EmpDashBoard';");
        out.println("  }");
        out.println("});");
        out.println("</script>");
        out.println("</body></html>");

        out.close();
    }
}
