<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Project Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <jsp:include page="nav.jsp"/>
    </header>
    <main>
        <form action="Controller?command=AddProject" method="post" novalidate="novalidate">
            <p><label for="projectName">Name</label><input type="text" id="projectName" name="projectName"> </p>
            <p><label for="start">Startdate</label><input type="date" id="start" name="start" min="2022-01-01" max="2030-12-31"></p>
            <p><label for="end">Enddate</label><input type="date" id="end" name="end" min="2022-01-01" max="2030-12-31"></p>
            <p><input type="submit" id="signUp" value="Submit"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
