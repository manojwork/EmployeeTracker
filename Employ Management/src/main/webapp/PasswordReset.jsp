<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #343a40;
        }
        .password-reset-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .password-reset-container h2 {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("begin");
        return;
    }
    response.setHeader("Cache-Control", "no-cache, no-store");
%>

    <div class="password-reset-container">
        <h2>Password Reset</h2>
        <form method="post" action="PasswordReset" onsubmit="return validate()">
            <div class="form-group">
                <label for="employeeId">Employee ID</label>
                <input name="empId" type="text" class="form-control" id="employeeId" placeholder="Enter your employee ID" required>
            </div>
            <div class="form-group">
                <label for="oldPassword">Old Password</label>
                <input name="oldPassword" type="password" class="form-control" id="oldPassword" placeholder="Enter your old password" required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input name="newPassword" type="password" class="form-control" id="newPassword" placeholder="Enter your new password" required>
            </div>
            <div class="form-group">
                <label for="conPassword">Confirm New Password</label>
                <input name="conPassword" type="password" class="form-control" id="conPassword" placeholder="Confirm your new password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Reset Password</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    function validate(){
        var newPassword = document.getElementById("newPassword").value;
        var conPassword = document.getElementById("conPassword").value;
        var oldPassword = document.getElementById("oldPassword").value;

        if (newPassword !== conPassword) {
            alert("The new passwords do not match.");
            return false;
        }

        return true;
    }
    </script>
</body>
</html>
