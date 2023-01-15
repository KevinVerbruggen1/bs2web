<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>XXX</span></h1>
    <jsp:include page="nav.jsp"/>
<h2>
Register
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

    <form action="Controller?command=Register" method="post" novalidate="novalidate">
        <p><label for="lastName">Name</label><input type="text" id="lastName" name="lastName" value="${lastNamePreviousValue}" class="${lastNameHasErrors? 'error' : ''}"> </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" value="${firstNamePreviousValue}" class="${firstNameHasErrors? 'error' : ''}" name="firstName"> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailPreviousValue}" class="${emailHasErrors? 'error' : ''}"></p>
        <p><label for="password">Password</label><input type="password" id="password" name="password" class="${passwordNameHasErrors? 'error' : ''}"> </p>
        <p>
            <label for="team">Team</label>
            <select id="team" name="team">
                <option value="ALPHA" ${teamPreviousValue eq 'ALPHA'?'selected':''}>ALPHA</option>
                <option value="BETA" ${teamPreviousValue eq 'BETA'?'selected':''}>BETA</option>
                <option value="GAMMA" ${teamPreviousValue eq 'GAMMA'?'selected':''}>GAMMA</option>
                <option value="DELTA" ${teamPreviousValue eq 'DELTA'?'selected':''}>DELTA</option>
                <option value="EPSILON" ${teamPreviousValue eq 'EPSILON'?'selected':''}>EPSILON</option>
            </select>
        </p>

        <p><input type="submit" id="signUp" value="Submit"></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
