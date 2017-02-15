<%--
  Created by IntelliJ IDEA.
  User: ladan
  Date: 2/15/17
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>

    <title>Add Meal</title>
</head>
<body>
<script>
    $(function() {
        $('input[name=dob]').datepicker();
    });
</script>

<form method="POST" action='MealServlet' name="frmAddMeal">
    User ID : <input type="text"  name="id"
                     value="<c:out value="${meal.id}" />" /> <br />

    <!--Date : <input
        type="text" name="date"
        value="<fmt:formatDate pattern="MM/dd/yyyy" value="${meal.dateTime}" />" /> <br /> -->
    Description : <input  type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    <input
            type="submit" value="Submit" />
</form>
</body>
</html>
