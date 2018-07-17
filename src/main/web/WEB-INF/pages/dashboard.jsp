<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
          integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script>
        <%@include file="../../resources/script/index.js"%>
    </script>
    <style>
        <%@include file="../../resources/css/styles.css"%>
    </style>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</head>
<body>
<h1 class="alert alert-primary">
    <a href="/bank-app/payBill"><button type="submit" class="btn btn-secondary goToDashboard" id="goToDashboard">Pay Bill</button></a>
    Dashboard
    <a href="logout"><button type="submit" class="btn btn-primary logout" id="logout">Logout</button></a>
</h1>
<div id="balance_board">
    <h2>Your balance</h2>
    <div id="balance_value">$${account.balance}</div>
</div>
<div id="dashboard_buttons">
    <button type="button" class="btn btn-primary" id="make_transfer">Make Transfer</button>
    <button type="button" class="btn btn-primary" id="view_transactions">View Transactions</button>
    <a href="/bank-app/payBill">
        <button type="button" class="btn btn-primary">Pay Bill</button>
    </a>
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
        <div id="loaderDiv">Loading...</div>
        <div id="no_account"></div>
    </div>
    <div id="transaction_list">
        <table id="table_id">
            <div class="bold_text">Transaction List</div>
            <div id=""></div>
            <thead>
                <tr>
                    <th>Recipient</th>
                    <th>Transaction Type</th>
                    <th>Amount</th>
                    <th>Transaction Date</th>
                </tr>
            </thead>
        </table>

    </div>
</div>
<div id="transfer_form_arena">
    <form id="transfer_form">
        <div class="form-group">
            <label for="recipient_name">Recipient Name</label>
            <input type="text" class="form-control" name="recipient_name" id="recipient_name" disabled>
        </div>
        <div class="form-group">
            <label for="recipient_email">Recipient Email</label>
            <input type="email" class="form-control" name="recipient_email" id="recipient_email" disabled>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control" name="recipient_account" id="recipient_account" disabled>
        </div>
        <div class="form-group">
            <label for="amount">Amount</label>
            <input type="text" class="form-control" id="amount" name="amount" value="" placeholder="Enter amount">
        </div>
        <div class="form-group">
            <label for="narration">Narration</label>
            <input type="text" class="form-control" id="narration" name="narration" value="" placeholder="e.g for gifts">
        </div>
        <button type="submit" class="btn btn-primary" id="transfer">Send</button>
    </form>
</div>
</body>
</html>
