<%@ page contentType="text/html;charset=UTF-8"
         import="com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String msg = request.getParameter("msg");
    String err = request.getParameter("error");
%>
<html>
<head>
    <title>Profile - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span class="brand">Swift<span>Food</span></span>
    <span class="user-info">
        Hello, <%= user.getUsername() %> |
        <a href="menu.jsp">🍽️ Menu</a> |
        <a href="orderHistory.jsp">📋 My Orders</a> |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <div class="profile-card">
        <div class="profile-header">
            <div class="profile-avatar">👤</div>
            <h2><%= user.getUsername() %></h2>
            <p><%= user.getEmail() %></p>
            <span class="membership-badge <%= user.getUserType().toLowerCase() %>">
                <%= user.getUserType() %> Member
            </span>
        </div>
        <div class="profile-body">
            <% if ("updated".equals(msg)) { %>
                <p class="success">✅ Profile updated successfully!</p>
            <% } %>
            <% if ("pwdchanged".equals(msg)) { %>
                <p class="success">✅ Password changed successfully!</p>
            <% } %>
            <% if (err != null) { %>
                <p class="error">❌ <%= err %></p>
            <% } %>

            <h3 style="margin-bottom:20px; color:#1A1A2E;">Edit Profile</h3>
            <form action="updateProfile" method="post">
                <div class="profile-field">
                    <label>Username</label>
                    <input type="text" value="<%= user.getUsername() %>"
                           readonly/>
                </div>
                <div class="profile-field">
                    <label>Email</label>
                    <input type="text" value="<%= user.getEmail() %>"
                           readonly/>
                </div>
                <div class="profile-field">
                    <label>Phone Number</label>
                    <input type="text" name="phone"
                           value="<%= user.getPhone() != null ? user.getPhone() : "" %>"
                           placeholder="Enter phone number"/>
                </div>
                <div class="profile-field">
                    <label>Delivery Address</label>
                    <textarea name="address" rows="3"
                              placeholder="Enter delivery address"><%= user.getAddress() != null ? user.getAddress() : "" %></textarea>
                </div>
                <button type="submit" class="btn">💾 Save Changes</button>
            </form>

            <hr style="margin: 32px 0;"/>

            <h3 style="margin-bottom:20px; color:#1A1A2E;">Change Password</h3>
            <form action="changePassword" method="post">
                <div class="profile-field">
                    <label>Current Password</label>
                    <input type="password" name="currentPassword"
                           placeholder="Enter current password"/>
                </div>
                <div class="profile-field">
                    <label>New Password</label>
                    <input type="password" name="newPassword"
                           placeholder="Enter new password"/>
                </div>
                <div class="profile-field">
                    <label>Confirm New Password</label>
                    <input type="password" name="confirmPassword"
                           placeholder="Confirm new password"/>
                </div>
                <button type="submit" class="btn">🔒 Change Password</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>