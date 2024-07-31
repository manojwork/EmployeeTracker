

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.classes.*;


@WebServlet("/ETask")
public class ETask extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = Integer.parseInt(request.getParameter("taskid"));
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Employee emp=(Employee)session.getAttribute("employee");
		TaskDB tdb = new TaskDB();
		Task t=tdb.getTask(emp.getEmp_id(), id);
		
		if(emp != null && t != null ) {
		    String taskId = URLEncoder.encode(t.getTaskid()+"", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/editTask.jsp?taskid="+taskId);
		}
		
		
	}

	protected  void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("taskid"));
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
		HttpSession session = request.getSession();
		Employee emp = (Employee)session.getAttribute("employee");
		String empid = emp.getEmp_id();
		
		TaskDB tdb  = new TaskDB();
		
		int t=tdb.getHours(empid,date);
		
		PrintWriter out = response.getWriter();
		
		Task task = tdb.getTask(empid, id);
		
		if ((t-task.getTotal())+total <= (8*60) && tdb.checkVForUpdate(empid, startTime, endTime, date,id)) {
			
			boolean b =tdb.updateTask(id,title,description,startTime,endTime,total,date);
			
			out.println("<html><head>");
	        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
	        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
	        out.println("<title>Edit Task</title></head><body>");
	        out.println("<script>");

	        if (b) {
	            out.println("Swal.fire({");
	            out.println("  title: 'Edited!',");
	            out.println("  text: 'Your task has been Edited.',");
	            out.println("  icon: 'success'");
	            out.println("}).then(() => { window.location.href = 'empDashboard'; });");
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
		else {
			out.println("<html><head>");
	        out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
	        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
	        out.println("<title>Edit Task</title></head><body>");
	        out.println("<script>");
	        out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'failed edit task  your task.',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });");
            out.println("</script>");
	        out.println("</body></html>");
	        out.close();
		}
	}


}