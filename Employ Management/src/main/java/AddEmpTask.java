

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class AddEmpTask
 */
@WebServlet("/AddEmpTask")
public class AddEmpTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("AddEmpTask.jsp?empid="+request.getParameter("empid"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		int minStart = Integer.parseInt(request.getParameter("minStart"));
		int minEnd = Integer.parseInt(request.getParameter("minEnd"));
		int total=((end*60)+minEnd)-((start*60)+minStart);
		String date = request.getParameter("date");
		float startTime = Float.parseFloat(start+"."+minStart);
		float endTime = Float.parseFloat(end+"."+minEnd);
		String empid = request.getParameter("empid");
		
		TaskDB tdb  = new TaskDB();
		
		int t=tdb.getHours(empid,date);
		
		PrintWriter out = response.getWriter();
		
		
		if (t+total <= (8*60) && tdb.checkVacancy(empid, startTime, endTime, date)) {
			
			boolean b =tdb.addTask(title,description,startTime,endTime,total,date,empid);
			out.println("<html><head>");
	        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
	        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
	        out.println("<title>Add Task</title></head><body>");
	        out.println("<script>");

			if (b) {
	            out.println("Swal.fire({");
	            out.println("  title: 'Added!',");
	            out.println("  text: 'Your task has been Added.',");
	            out.println("  icon: 'success'");
	            out.println("}).then(() => { window.location.href = 'TaskEmp?empid="+empid+"'; });");
	        } else {
	            out.println("Swal.fire({");
	            out.println("  title: 'Error',");
	            out.println("  text: 'There was an error while Adding your task.',");
	            out.println("  icon: 'error'");
	            out.println("}).then(() => { history.back(); });");
	        }

	        out.println("</script>");
	        out.println("</body></html>");
	        out.close();
		}
		else {
			out.println("<html><head>");
	        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
	        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
	        out.println("<title>Add Task</title></head><body>");
	        out.println("<script>");
	        out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'There was an error while Adding your task.',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });");
            out.println("</script>");
	        out.println("</body></html>");
	        out.close();
		}
		
	}

}
