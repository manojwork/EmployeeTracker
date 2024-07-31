

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PasswordReset")
public class PasswordReset extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empid=request.getParameter("empId");
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		EmpDB empDB= new EmpDB();
		boolean b = empDB.updatePassword(empid,oldPassword,newPassword);
		
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
        out.println("<title>Edit Task</title></head><body>");
        out.println("<script>");
        if (b) {
            out.println("Swal.fire({");
            out.println("  title: 'Updated!',");
            out.println("  text: 'Your password has been Updated.',");
            out.println("  icon: 'success'");
            out.println("}).then(() => { window.location.href = 'LogOut'; });");
        } else {
            out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'There was an error while Updating your password.',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });");
        }

        out.println("</script>");
        out.println("</body></html>");
        out.close();

		
	}

}
