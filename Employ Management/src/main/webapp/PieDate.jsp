<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Task</title>
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

	if (session.getAttribute("employee") == null){
		response.sendRedirect("begin");
	}
	response.setHeader("Cache-Control", "no-cache, no-store,");


%>


<div class="container">
    <button onclick="history.back()" class="btn btn-primary back-button">Back</button>
    <h2> Select Date To view Pie Chart</h2>
    <form method="post" action="PieChart" onsubmit="return validate()">

        <div class="form-group">
            <label for="date">Date:</label>
            <input name="date" type="date" class="form-control" id="date" required>
        </div>
        
        <button type="submit" class="btn btn-success submit-button">Submit</button>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>

    document.addEventListener('DOMContentLoaded', function() {
        const dateInput = document.getElementById("date");
        const today = new Date().toISOString().split("T")[0];
        dateInput.setAttribute('min', today);
    });

    function validate() {
        var start = parseInt(document.getElementById("start").value);
        var end = parseInt(document.getElementById("end").value);
        var minStart = parseInt(document.getElementById("minStart").value) || 0;
        var minEnd = parseInt(document.getElementById("minEnd").value) || 0;

        if (start > end || (start === end && minStart > minEnd) || (start === end && minStart === minEnd)) {
            alert("The Start Time must be earlier than End Time");
            return false;
        }

        return true;
    }
</script>

</body>
</html>
