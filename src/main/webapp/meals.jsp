<%--
  Created by IntelliJ IDEA.
  User: ladan
  Date: 2/13/17
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Meals list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meals list</h2>


    <table border=1>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan=2>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  var="meal" items="${mealList}">
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">

                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>


        </tbody>
    </table>
</body>
</html>
