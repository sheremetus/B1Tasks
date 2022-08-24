<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirja
  Date: 24.08.2022
  Time: 17:31
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

            <tr>
                <c:forEach items="${data}" var="path">
                    <td >
                            ${path}
                    </td>
                </c:forEach>
            </tr>
    </table>
</c:if>
</body>
</html>
