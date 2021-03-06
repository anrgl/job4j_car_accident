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
                <th>Type</th>
                <th>Rule</th>
                <th>Text</th>
                <th colspan="2">Address</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td>${accident.getId()}</td>
                    <td>${accident.getName()}</td>
                    <td>${accident.getType().getName()}</td>
                    <td>
                        <c:forEach var="rule" items="${accident.getRules()}">
                        <p>${rule.getName()}</p>
                        </c:forEach>
                    </td>
                    <td>${accident.getText()}</td>
                    <td>${accident.getAddress()}</td>
                    <td><a href="<c:url value="/edit?id=${accident.getId()}" />" class="btn btn-primary">edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
