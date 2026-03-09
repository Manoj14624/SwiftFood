<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-card">
    <h2>Create Account</h2>
    <% String err = request.getParameter("error");
       if(err == null) err = (String) request.getAttribute("error"); %>
    <% if (err != null) { %>
        <p class="error">Registration failed. Try again.</p>
    <% } %>
    <form action="register" method="post">
        <input type="text"     name="username" placeholder="Username"         required/>
        <input type="password" name="password" placeholder="Password"         required/>
        <input type="email"    name="email"    placeholder="Email"            required/>
        <input type="text"     name="phone"    placeholder="Phone Number"     />
        <textarea              name="address"  placeholder="Delivery Address"></textarea>
        <button type="submit" class="btn">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login</a></p>
</div>
</body>
</html>