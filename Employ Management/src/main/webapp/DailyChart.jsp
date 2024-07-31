<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <title>Daily Hours Bar Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>Daily Hours Bar Chart</h2>
    <canvas id="myBarChart" width="400" height="200"></canvas>
    <script>
        // Fetching data from request attributes
        var dates = [];
        var hours = [];

        <% 
            ArrayList<String> dateList = (ArrayList<String>) request.getAttribute("dates");
            ArrayList<Integer> hourList = (ArrayList<Integer>) request.getAttribute("hours");
            for (int i = 0; i < dateList.size(); i++) {
        %>
                dates.push('<%= dateList.get(i) %>');
                hours.push(<%= hourList.get(i) %>);
        <% } %>

        // Setting up the bar chart
        var ctx = document.getElementById('myBarChart').getContext('2d');
        var myBarChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: dates,
                datasets: [{
                    label: 'Hours Worked',
                    data: hours,
                    backgroundColor: 'rgba(255, 205, 86, 0.8)',
                    borderColor: 'rgba(255, 205, 86, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    x: {
                        title: {
                            display: true,
                            text: 'Dates'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Time in Minutes'
                        },
                        beginAtZero: true
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                }
            }
        });
    </script>
</body>
</html>
