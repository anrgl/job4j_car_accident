<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title>Registration::Accident</title>
</head>
<body>
<div class="container">
    <div class="row">
        <form name='login' action="<c:url value='/reg'/>" method='POST'>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username"
                       aria-label="Username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="text" name="password" id="password" class="form-control" placeholder="Password"
                       aria-label="Password">
            </div>
            <input name="submit" type="submit" value="submit" class="btn btn-primary">
        </form>
    </div>
</div>
</body>
</html>
