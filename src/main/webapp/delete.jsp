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
            Delete User ${userId}
        </h2>

    </header>
    <main>
        <form action="Controller?command=Edit&userId=${userId}&editUser=true" method="post" novalidate="novalidate">

            <p><input type="submit" id="Yes" value="Yes" formaction="Controller?command=Delete&delete=true&userId=${userId}"></p>
            <p><input type="submit" id="No" value="No" formaction="Controller?command=Delete&delete=false&userId=${userId}"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
