<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row">
        <a href="<c:url value="/create" />">Добавить инцидент</a>
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Text</th>
                <th colspan="2">Address</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td>${accident.value.getId()}</td>
                    <td>${accident.value.getName()}</td>
                    <td>${accident.value.getText()}</td>
                    <td>${accident.value.getAddress()}</td>
                    <td><a href="<c:url value="/edit?id=${accident.value.getId()}"  />" class="btn btn-primary">edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
