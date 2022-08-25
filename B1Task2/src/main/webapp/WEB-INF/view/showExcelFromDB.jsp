<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirja
  Date: 24.08.2022
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3307/b2_second_task_schema?useSSL=false"
                   user="root" password="admin"/>

<sql:query dataSource="${snapshot}" var="accounts">
    SELECT * from accounts;
</sql:query>

<sql:query dataSource="${snapshot}" var="classes">
    SELECT * from classes;
</sql:query>

<sql:query dataSource="${snapshot}" var="closingBalance">
    SELECT * from closing_balance;
</sql:query>

<sql:query dataSource="${snapshot}" var="moneyTurnover">
    SELECT * from money_turnover;
</sql:query>

<sql:query dataSource="${snapshot}" var="openingBalance">
    SELECT * from opening_balance;
</sql:query>

<table border="1" width="10%" style="float: left">
    <tr>
        <th>Accounts</th>
    </tr>
    <c:forEach var="row" items="${accounts.rows}">
        <tr>
            <td><c:out value="${row.idaccounts}"/></td>
        </tr>
    </c:forEach>
</table>


<table border="1" width="12%" style="float: left">
    <tr>
        <th>Classes</th>
        <th>ClosingBalance: Assets</th>
        <th>ClosingBalance: Liability</th>
    </tr>
    <c:forEach var="ClosingBalanceRow" items="${closingBalance.rows}">
        <tr>
            <td><c:out value="${ClosingBalanceRow.classes_idclasses}"/></td>
            <td><c:out value="${ClosingBalanceRow.assets}"/></td>
            <td><c:out value="${ClosingBalanceRow.liability}"/></td>

        </tr>
    </c:forEach>
</table>

<table border="1" width="12%" style="float: left">
    <tr>
        <th>MoneyTurnover: Debit</th>
        <th>MoneyTurnover: Credit</th>
    </tr>
    <c:forEach var="MoneyTurnoverRow" items="${moneyTurnover.rows}">
        <tr>
            <td><c:out value="${MoneyTurnoverRow.debit}"/></td>
            <td><c:out value="${MoneyTurnoverRow.credit}"/></td>

        </tr>
    </c:forEach>
</table>
<table border="1" width="12%" style="float: left">
    <tr>
        <th>OpeningBalance: Assets</th>
        <th>OpeningBalance: Liability</th>
    </tr>
    <c:forEach var="OpeningBalanceRow" items="${openingBalance.rows}">
        <tr>

            <td><c:out value="${OpeningBalanceRow.assets}"/></td>
            <td><c:out value="${OpeningBalanceRow.liability}"/></td>

        </tr>
    </c:forEach>
</table>


</body>
</html>
