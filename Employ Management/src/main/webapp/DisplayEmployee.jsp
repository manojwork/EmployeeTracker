<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            background-color: #343a40;
            display: flex;
            width: 100vw;
            height: max-content;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        .transaction-history {
            width: 70vw;
            height: 75vh;
            overflow: scroll;
        }
        .buttons{
            display: flex;
            align-items: center;
            justify-content: center;
            width: 80vw;
            height: 10vh;
        }
        button{
            margin: 10px;        }
    </style>
</head>
<body>

<%

    if (session.getAttribute("employee") == null) {
        response.sendRedirect("begin");
    }
    response.setHeader("Cache-Control", "no-cache, no-store,");

%>

   <div class="buttons">
    <button onclick="history.back()" class="btn btn-primary back-button">Back</button>

    <form method="post" action="LogOut" class="logout">
        <button type="submit" class="btn btn-danger">Logout</button>
    </form>
   </div>

<div class="card">
    <div class="card-body">
        <h5 class="card-title d-flex justify-content-between align-items-center">
            Employees
            <a href="AddEmployee.jsp" class="btn btn-primary">Add New Employee</a>
        </h5>
        <div class="atm-card transaction-history">
            <table id="transaction-table" class="table table-striped">
                <!-- Table content will be populated by JavaScript -->
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
function fetchTransactionHistory() {
    fetch('DisplayEmp')
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
