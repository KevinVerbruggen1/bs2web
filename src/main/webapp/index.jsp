<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>XXX</span>
			</h1>
			<jsp:include page="nav.jsp"/>
		</header>
		<main>
			<c:choose>
				<c:when test="${user==null}">
					<article>
						<h2>Please Log In</h2>
						<c:if test="${not empty errors}">
							<div id="error" class="alert alert-danger">
								<ul>
									<c:forEach items="${errors}" var="error">
										<li id="errorItem" class="error">${error}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>

						<form action="Controller?command=Login" method="post" novalidate="novalidate">
							<p><label for="email">Email</label><input type="email" id="email" name="email" required></p>
							<p><label for="password">Password</label><input type="password" id="password"  name="password" required> </p>

							<p><input type="submit" id="signIn" value="Submit"></p>

						</form>
					</article>
				</c:when>
				<c:otherwise>
					<section>
						<h2>Welcome, ${user.getFirstName()}</h2>
						<form action="Controller?command=Logout" method="post" novalidate="novalidate">
							<p><input type="submit" id="logOut" value="Log out"></p>
						</form>
					</section>
				</c:otherwise>
			</c:choose>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>