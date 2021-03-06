<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title>Create</title>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="<c:url value='/save' />" method="post">
            <table>
                <tr>
                    <td>
                        <label for="name" class="form-label">Name: </label>
                        <input type="text" id="name" class="form-control" name="name">
                    </td>
                    <td>
                        <label for="type" class="form-label">Type: </label>
                        <select name="type.id" id="type" class="form-select">
                            <c:forEach var="type" items="${types}">
                                <option value="${type.id}">${type.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <label for="rule" class="form-label">Rule: </label>
                        <select name="rIds" id="rule" class="form-select" multiple>
                            <c:forEach var="rule" items="${rules}">
                                <option value="${rule.id}">${rule.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <label for="text" class="form-label">Text: </label>
                        <input type="text" id="text" class="form-control" name="text">
                    </td>
                    <td>
                        <label for="address" class="form-label">Address: </label>
                        <input type="text" id="address" class="form-control" name="address">
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" class="btn btn-primary" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
