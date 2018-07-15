
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='../../resources/css/styles.css'/>" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script>
        <%@include file="../../resources/script/index.js"%>
    </script>
</head>
<body>
    <h1 class="alert alert-primary">Dashboard</h1>
    <div id="balance_board">
        <h2>Your balance</h2>
        <div id="balance_value">$569.30</div>
    </div>
    <div id="dashboard_buttons">
        <button type="button" class="btn btn-primary" id="make_transfer">Make Transfer</button>
        <button type="button" class="btn btn-primary" id="view_transactions">View Transactions</button>
        <button type="button" class="btn btn-primary">Pay Bill</button>
    </div>

    <div id="arena">
        <div id="transfer_arena">
            <form class="form-inline" id="recipient_form">
                <div class="form-group mx-sm-3 mb-2">
                    <label for="recipient" class="sr-only">Recipient</label>
                    <input type="text" class="form-control" id="recipient" name="recipient" placeholder="Account number">
                </div>
                <button type="submit" class="btn btn-success mb-2 confirm_recipient">Confirm recipient</button>
            </form>
        </div>
        <div id="transaction_list">
            <p>Transaction List</p>
        </div>
    </div>
    <div id="transfer_form">
        <form class="transfer_form">
            <div class="form-group">
                <label for="recipient_name">Recipient Name</label>
                <input type="text" class="form-control" id="recipient_name">
            </div>
            <div class="form-group">
                <label for="amount">Amount</label>
                <input type="text" class="form-control" id="amount" placeholder="Enter amount">
            </div>
            <button type="submit" class="btn btn-primary">Send</button>
        </form>
    </div>
</body>
</html>
