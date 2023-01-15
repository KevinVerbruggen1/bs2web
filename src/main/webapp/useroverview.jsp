<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>XXX</span></h1>
    <jsp:include page="nav.jsp"/>
<h2>
User Overview
</h2>

</header><main>
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
        <th>User ID</th>
        <th>Last Name</th>
        <th>First Name</th>
        <th>E-mail</th>
        <th>Team</th>
        <th>Role</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.getUserid()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getFirstName()}</td>
                <td id="email">${user.getEmail()}</td>
                <td>${user.getTeam()}</td>
                <td>${user.getRole()}</td>
                <td><a class="icon" id="edit" href="Controller?command=Edit&userId=${user.getUserid()}"><img src="images/pencil.png" alt="Prullenbak"></a></td>
                <td><a class="icon" id="delete" href="Controller?command=Delete&userId=${user.getUserid()}"><img src="images/bin.png" alt="Prullenbak"></a></td>
            </tr>
        </c:forEach>
        </tbody>
<caption>Users Overview</caption>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>