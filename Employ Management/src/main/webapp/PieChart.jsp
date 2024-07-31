<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <title>Pie Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
     #myPieChart {
            width: 1500px !important;
            height: 500px !important;
        }
    </style>
</head>
<body>

    <h2>Task Distribution Pie Chart</h2>
    <a href="history.back()" style="background-color:tomato;padding:10px;border:0;color:white;border-radius:10px;text-decoration:none;">Back</a>
    <canvas id="myPieChart"></canvas>
    <script>
        // Fetching data from request attributes
        var labels = [];
        var hours = [];

        <% 
            ArrayList<String> labels = (ArrayList<String>) request.getAttribute("labels");
            ArrayList<Integer> hours = (ArrayList<Integer>) request.getAttribute("hours");
            for (int i = 0; i < labels.size(); i++) {
        %>
                labels.push('<%= labels.get(i) %>');
                hours.push(<%= hours.get(i) %>);
        <% } %>

        // Setting up the pie chart
        var ctx = document.getElementById('myPieChart').getContext('2d');
        var myPieChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    data: hours,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
    </script>
</body>
</html>
