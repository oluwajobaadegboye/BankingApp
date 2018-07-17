<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Bill Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
          integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <style>
        <%@include file="../../resources/css/styles.css"%>
    </style>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script>
        <%@include file="../../resources/script/index.js"%>
        <%@include file="../../resources/script/bill.js"%>
    </script>
</head>
<body>
<h1 class="alert alert-primary">
    <a href="dashboard"><button type="submit" class="btn btn-secondary goToDashboard" id="goToDashboard">Dashboard</button></a>
    Bill Payment
    <a href="logout"><button type="submit" class="btn btn-primary logout" id="logout">Logout</button></a>
</h1>
<div id="balance_board">
    <h2>Pay</h2>
</div>
<div id="bill_form_arena">

    <form id="payBill_form">
        <label id="success"></label>
        <div class="form-group">
            <label for="bill_option">Bill Option</label>
            <select id="bill_option" name="bill_option" class="form-control">
                <c:forEach items="${billPayments}" var="bill" >
                    <option value="${bill.id}">${bill.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" name="description" id="description" disabled>
        </div>

        <div class="form-group">
            <label for="amount">Amount</label>
            <input type="text" class="form-control" id="amount" name="amount" value="" disabled>
        </div>

        <button type="submit" class="btn btn-primary" id="transfer">Pay Bill</button>
    </form>
</div>
</body>
</html>
