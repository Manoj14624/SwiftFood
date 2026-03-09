<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Object user = session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Order Placed - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span>SwiftFood</span>
    <a href="logout">Logout</a>
</div>
<div class="form-card" style="text-align:center">
    <h2>Order Placed Successfully!</h2>
    <p>Thank you for ordering from SwiftFood.</p>
    <p>Your food is being prepared.</p>
    <br/>
    <a href="menu.jsp" class="btn">Order More</a>
    <a href="logout" class="btn btn-outline">Logout</a>
</div>
</body>
</html>