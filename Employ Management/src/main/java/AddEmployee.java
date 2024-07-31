

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String project = request.getParameter("project");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		EmpDB empDB = new EmpDB();
		
		int id= empDB.getMaxID();
		
		String empid="E00"+(id+1);
		System.out.println(empid+ " is the user id ;");
		boolean b=empDB.addEmployee(empid,name,role,project,password);
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
        out.println("<title>Edit Task</title></head><body>");
        out.println("<script>");

        if (b) {
            out.println("Swal.fire({");
            out.println("  title: 'Edited!',");
            out.println("  text: 'Employee Added.',");
            out.println("  icon: 'success'");
            out.println("}).then(() => { window.location.href = 'DisplayEmployee.jsp'; });");
        } else {
            out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'There was an error while editing your task.',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });");
        }

        out.println("</script>");
        out.println("</body></html>");
        out.close();
	}
	
		
		
	}

