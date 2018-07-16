<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <style>
        <%@include file="../../resources/css/styles.css"%>
    </style>
</head>
<body>
<h1 class="alert alert-primary">Welcome to XYZ bank</h1>
<form id="login-form" action="login" method="post">
    <label id="errorMessage">${errorMessage}</label>
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp" placeholder="Enter Username">
    </div>
    <div class="form-group">
        <label for="user_password">Password</label>
        <input type="password" class="form-control" name="password" id="user_password" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="username">Email</label>
        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter Username">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp" placeholder="Enter Username">

    </div>
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp" placeholder="Enter Username">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Sign up</button>
        <a href="login">Or Log in</a>
    </div>
</form>
</body>
</html>

