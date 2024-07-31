<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.classes.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Employee</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            background-color: #343a40;
        }
        .container {
            margin-top: 50px;
            background-color: white;
            padding: 20px;
        }
        @media (min-width:1000px) {
            .container{
                width: 550px;
            }
        }
        .back-button {
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .submit-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<%

	if (session.getAttribute("employee") == null && session.getAttribute("admin") == null && request.getAttribute("employee") == null) {
		response.sendRedirect("begin");
		return;
	}
	response.setHeader("Cache-Control", "no-cache, no-store,");
	
	Employee employee = (Employee) request.getAttribute("employee");

%>


<div class="container">
    <button onclick="history.back()" class="btn btn-primary back-button">Back</button>
    <h2>Add Employee</h2>
    <form method="post" action="<%=request.getContextPath()%>/EditEmployee" >
    <div class="form-group">
            <label for="name">Employee Id:</label>
            <input name="empid" type="text" value="<%= employee.getEmp_id() %>" class="form-control" id="name" placeholder="Name" readonly>
            
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input name="name" type="text" value="<%= employee.getName() %>" class="form-control" id="name" placeholder="Name" required>
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <input name="role" type="text" value="<%= employee.getRole() %>" class="form-control" id="role" required>
        </div>
        <div class="form-group">
            <label>Project:</label>
            <div class="form-inline">
                <input name="project" type="text"  value="<%= employee.getProject() %>" class="form-control mr-2" id="project" placeholder="Project"  required>
            </div>
        </div>
        <div class="form-group">
            <label>Password:</label>
            <div class="form-inline">
                <input  name="password" type="text" class="form-control mr-2" value="<%= employee.getPassword() %>" id="password" placeholder="Password"  required>
            </div>
        </div>
        
        <button type="submit" class="btn btn-success submit-button">Submit</button>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
