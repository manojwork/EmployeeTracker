<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.classes.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #343a40;
            padding: 20px;
        }
        .task-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header-buttons {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
        .task-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .task-details {
            font-size: 18px;
        }
        p {
            font-weight: 700;
            color: green;
        }
        span {
            font-weight: 400;
            color: black;
        }
    </style>
</head>
<body>

<%
    // Debugging lines to check session and request attributes
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("begin");
        return;
    }

    if (request.getAttribute("task") == null) {
        response.sendRedirect("begin");
        return;
    }

    response.setHeader("Cache-Control", "no-cache, no-store");

    Task task = (Task) request.getAttribute("task");

    if (task == null) {
        response.sendRedirect("begin");
        return;
    }
%>

<div class="container task-container">
    <div class="row">
        <div class="col-12">
            <div class="header-buttons">
                <button type="button" class="btn btn-primary" onclick="history.back()">Back</button>
                <form action="LogOut" method="post">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </form>
            </div>
            <div class="task-title" id="title"><%= task.getTitle() %></div>
            <div class="task-details">
                <p>Duration: <span id="duration"><%= task.getTotal() %> mins</span></p>
                <p>Date: <span id="date"><%= task.getDate() %></span></p>
                <p>Start Time: <span id="startTime"><%= task.getTiming()[0] %></span></p>
                <p>End Time: <span id="endTime"><%= task.getTiming()[1] %></span></p>
                <p>Description: <span id="description"><%= task.getDescription() %></span></p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
