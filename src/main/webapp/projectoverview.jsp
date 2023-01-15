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
        <c:if test="${not empty errors}">
            <div id="error" class="alert alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li id="errorItem" class="error">${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <table>
            <tr>
                <th>Project ID</th>
                <th>Name</th>
                <th>Team</th>
                <th>Start</th>
                <th>End</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.getProjectId()}</td>
                    <td>${project.getName()}</td>
                    <td>${project.getTeam()}</td>
                    <td>${project.getStartDateString()}</td>
                    <td>${project.getEndDateString()}</td>
                    <td><a class="icon" id="edit" href="Controller?command=Edit&userId=${project.getProjectId()}"><img src="images/pencil.png" alt="Prullenbak"></a></td>
                    <td><a class="icon" id="delete" href="Controller?command=Delete&userId=${project.getProjectId()}"><img src="images/bin.png" alt="Prullenbak"></a></td>
                </tr>
            </c:forEach>
            </tbody>
            <caption>Projects Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
