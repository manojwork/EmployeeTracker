<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Task</title>
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
    <h2>Edit Task</h2>
    <form method="post" action="<%=request.getContextPath()%>/ETask" onsubmit="return validate()">
    <div class="form-group">
            <label for="title">Task id:</label>
            <input Style="outline:0;border:0;background-color:white;" name="taskid" type="text" class="form-control" id="id"  readonly >
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input name="title" type="text" class="form-control" id="title" placeholder="Enter task title" required>
        </div>
        <div class="form-group">
            <label for="date">Date:</label>
            <input name="date" type="date" class="form-control" id="date" required>
        </div>
        <div class="form-group">
            <label>Start Time (0-24 hours):</label>
            <div class="form-inline">
                <input name="start" type="number" class="form-control mr-2" id="start" placeholder="hours" min="0" max="24" required>
                <input name="minStart" type="number" class="form-control" id="minStart" placeholder="minutes" min="0" max="59" value=0>
            </div>
        </div>
        <div class="form-group">
            <label>End Time (0-24 hours):</label>
            <div class="form-inline">
                <input  name="end" type="number" class="form-control mr-2" id="end" placeholder="hours" min="0" max="24" required>
                <input name="minEnd" type="number" class="form-control" id="minEnd" placeholder="minutes" min="0" max="59" value=0>
            </div>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea required name="description" class="form-control" id="description" rows="4" placeholder="Type here..."></textarea>
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

        if (start > end || (start === end && minStart > minEnd) || (start === end && minStart === minEnd) ) {
            alert("The Start Time must be earlier than End Time");
            return false;
        }

        return true;
    }
    
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

    
    
    var taskid = getUrlParameter("taskid");
    document.getElementById("id").value = taskid;
    
</script>

</body>
</html>
