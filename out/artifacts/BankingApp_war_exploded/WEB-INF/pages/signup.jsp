<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='../../resources/css/styles.css'/>" />
</head>
<body>
<h1 class="alert alert-primary">Welcome to XYZ bank</h1>
<form id="login-form" action="/" method="post">
    <div class="form-group">
        <label for="user_email">Email address</label>
        <input type="email" class="form-control" name="email" id="user_email" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="user_password">Password</label>
        <input type="password" class="form-control" name="password" id="user_password" placeholder="Password">
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="remember_me">
        <label class="form-check-label" for="remember_me">Remember me</label>
    </div>
    <button type="submit" class="btn btn-primary">Sign up</button>
</form>
</body>
</html>

