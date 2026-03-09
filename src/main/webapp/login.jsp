<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-card">
    <h2>Login to SwiftFood</h2>
    <% String msg = request.getParameter("msg");
       String err = request.getParameter("error"); %>
    <% if ("registered".equals(msg)) { %>
        <p class="success">Registered successfully! Please login.</p>
    <% } %>
    <% if (err != null) { %>
        <p class="error"><%= err %></p>
    <% } %>
    <form action="login" method="post">
        <input type="text"     name="username" placeholder="Username" required/>
        <input type="password" name="password" placeholder="Password" required/>
        <button type="submit" class="btn">Login</button>
    </form>
    <p>New user? <a href="register.jsp">Register here</a></p>
</div>
</body>
</html>