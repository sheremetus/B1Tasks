<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Excel Processing</title>
</head>
<body>
<div class="container">

    <c:url value="/uploadFile" var="fileUploadControllerURL"/>
    <div class="row">

        <div class="col-lg-12">

            <form action="${fileUploadControllerURL}" method="post"
                  enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><b>File:</b></td>
                        <td><input type="file" name="file"></td>
                        <td><input type="submit" value="загрузить файл"></td>
                    </tr>
                </table>
            </form>

            <form action="/excel" method="post">
                <input type="submit" name="button" value="Загрузить файл в базу данных"/>

            </form>
            <form action="/showExcel" method="get">
                <input type="submit" name="button" value="Показать загруженный файл на веб странице"/>

            </form>
            <br/>
            <form action="/showExcelsInDb" method="get">
                <input type="submit" name="button" value="Показать список загруженных файлов в базу данных"/>

            </form>
            <form action="/showExcelFromDB" method="get">

                <input type="submit" name="button" value="Просмотреть файл загруженный в базу данных"/>
            </form>
        </div>

    </div>
    <hr>

</div>

</body>
</html>