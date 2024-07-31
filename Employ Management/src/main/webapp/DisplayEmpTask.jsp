<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.classes.*,java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 20px;
            background-color:#343a40 ;
            color: white;
        }
        .task-table th, .task-table td {
            text-align: center;
            vertical-align: middle;
        }
        .task-table {
            margin-top: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .header button {
            margin-left: 10px;
        }
        table{
            background-color: white;
        }
    </style>
</head>
<body>

<%

	if (session.getAttribute("employee") == null && session.getAttribute("admin") == null){
		response.sendRedirect("begin");
		return;
	}
	response.setHeader("Cache-Control", "no-cache, no-store,");


%>


		<% ArrayList<Task> tasks =  (ArrayList<Task>) request.getAttribute("tasks");  
		
		String empid = request.getParameter("empid");
		
		if (tasks==null||tasks.isEmpty()){
			out.println("No tasks ! <a>");
			out.println("<a class='btn btn-danger mb-3 href='history.back()'>Back</a>");
			out.println("<a href='AddEmpTask?empid=" + empid + "' class='btn btn-primary mb-3'>Add Task</a>");
			return;
		}
		
		%>
    <div class="container">
        <div class="header">
            <h2>Employee ID: <%= tasks.get(0).getEmpid() %> </h2>
            <div>
				<form method="post" action="LogOut">     
				                <a href='history.back()' class="btn btn-secondary">Back</a>
				           <button type="submit" class="btn btn-danger">Logout</button>
				</form>
            </div>
        </div>
 
        <a href='<%="AddEmpTask?&empid="+empid+"" %>' class="btn btn-primary mb-3">Add Task</a>

        <table class="table table-bordered task-table">
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>Title</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>View</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <% for(Task t : tasks){ %>
                
                <tr>
                    <td><%= t.getTaskid() %></td>
                    <td><%= t.getTitle() %></td>
                    <td><%= t.getTiming()[0] %></td>
                    <td><%= t.getTiming()[1] %></td>
                    <td><a  href='<%="VEmpTask?taskid=" + t.getTaskid() +"&empid="+empid+"" %>'  class="btn btn-info">View</a></td>
                    <td><a href='<%="EditEmpTask?taskid=" + t.getTaskid() +"&empid="+empid+"" %>' class="btn btn-warning">Edit</a></td>
                    <td><a href='<%="DEmpTask?taskid=" + t.getTaskid() +"&empid="+empid+"" %>' class="btn btn-danger">Delete</a></td>
                </tr>

                <%} %>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
