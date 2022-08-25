<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirja
  Date: 22.08.2022
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty data}">
    <table style="border: 1px solid black; border-collapse: collapse;">
<%--        Итерируемся по карте--%>
        <c:forEach items="${data}" var="row">
            <tr>
<%--                //Итерируемся по ячейкам--%>
                <c:forEach items="${row.value}" var="cell">
                    <td style="border:1px solid black;height:20px;width:100px;">
                            ${cell}
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
