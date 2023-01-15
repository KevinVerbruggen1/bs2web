<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@page isErrorPage="true" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Foutje</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <jsp:include page="nav.jsp"/>

    </header>
    <main>
        <h2>Oei</h2>
        <p>Er liep iets fout. Probeer nog een keertje... </p>
        <p>Info voor ontwikkelaars: dit liep er fout</p>
        <p>${pageContext.exception}</p>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>