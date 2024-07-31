<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.classes.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            overflow:hidden;
            background-color: #f8f9fa;
        }
        .header {
            background-color: #343a40;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h2 {
            flex-grow: 1;
            text-align: center;
            margin: 0;
        }
        .header .logout button {
            background-color: #dc3549;
            color: white;
            border: none;
        }
        .sidebar {
            background-color: #343a40;
            height: calc(100vh - 50px);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-evenly;
            color: white;
            padding-top: 20px;
        }
        .sidebar h5 {
            font-size: 25px;
            color: #ffc107;
        }
        .task-controls {
            margin-bottom: 20px;
        }
        .chart-container {
            margin-top: 20px;
            width: 80%;
        }
        .table-wrapper {
            margin-top: 20px;
        }
        .main-content {
            padding: 15px;
            background-color: #ffffff;
            border-radius: 8px;
            margin: 20px;
            flex: 1;
        }
        span {
            color: white;
            font-weight: 600;
        }
        .container-fluid {
            padding: 0;
        }
        .details {
            width: 80%;
            text-align: left;
        }
        .transaction-history{
        width:70vw;
        height:75vh;
        overflow:scroll;
        }
    </style>
</head>
<body>

<%

    if (session.getAttribute("employee") == null) {
        response.sendRedirect("begin");
        return;
    }
    response.setHeader("Cache-Control", "no-cache, no-store,");
    
    Employee employee = (Employee) session.getAttribute("employee");

%>

<div class="header">
    <h2>Welcome, <span id="head"><%= employee.getName() %></span></h2>
    <form method="post" action="LogOut" class="logout">
        <button type="submit" class="btn">Logout</button>
    </form>
</div>

<div class="container-fluid">
    <div class="row no-gutters">
        <div class="col-md-3 sidebar">
            <div class="details">
                <h5>Details of Employee</h5>
                <label>Employee Id</label>
                <span id="empid"><%= employee.getEmp_id() %></span><br>
                <label>Name</label>
                <span id="name"><%= employee.getName() %></span><br>
                <label>Role</label>
                <span id="role"><%= employee.getRole() %></span><br>
                <label>Project</label>
                <span id="project"><%= employee.getProject() %></span><br>
            </div>
			<form method="post" action="Trigger">
            <div class="chart-container">
                <h5>Graphs</h5>
                <div class="form-group">
                    <label for="categorySelect">Category:</label>
                    <select class="form-control" id="categorySelect" name="option">
                    	<option value="date">Day Pie Chart</option>
                        <option value="daily">Daily</option>
                        <option value="weekly">Weekly</option>
                        <option value="monthly">Monthly</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-danger mb-3" style="width:100%;" onclick="visualizeGraph()">Visualize the graph</button>
                 
            </div>
            </form>
            
             <form  method="post" action="PasswordReset.jsp">
        					<button  style="width:100%;"  type="submit" class="btn bg-primary text-white">Password Reset</button>
    				</form>
    			<% if (session.getAttribute("admin")!=null){ %>
    			<a style="margin-top: 10px; width:100%;" href ="DisplayEmployee.jsp" class="btn btn-danger mb-3" >Employees</a>
    			<% } %>
        </div>      
        <div class="col-md-9 d-flex justify-content-center">
            <div class="main-content">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title d-flex justify-content-between align-items-center">
                            Tasks
                            <a href="AddTask.jsp" class="btn btn-primary">Add New Task</a>
                        </h5>
                        <div class="atm-card transaction-history">
                            <table id="transaction-table" class="table table-striped">
                                <!-- Transaction history will be displayed here -->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>

function fetchTransactionHistory() {
    fetch('DisplayTasks')
        .then(response => response.text())
        .then(data => {
            // Update the table with the received transaction history
            document.getElementById('transaction-table').innerHTML = data;
        })
        .catch(error => console.error('Error fetching transaction history:', error));
}

fetchTransactionHistory();


</script>
</body>
</html>