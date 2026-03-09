<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.*, com.swiftfood.dao.*, com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Admin Dashboard - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span>Admin Dashboard</span>
    <span>Hello, <%= user.getUsername() %> |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <h2>Manage Orders</h2>
    <table class="menu-table">
        <tr>
            <th>Order #</th>
            <th>Customer</th>
            <th>Total</th>
            <th>Status</th>
            <th>Date</th>
            <th>Address</th>
            <th>Action</th>
        </tr>
        <%
            List<Map<String, Object>> orders = new OrderDAO().getAllOrders();
            for (Map<String, Object> o : orders) {
        %>
        <tr>
            <td><%= o.get("orderId") %></td>
            <td><%= o.get("username") %></td>
            <td>Rs. <%= o.get("total") %></td>
            <td><%= o.get("status") %></td>
            <td><%= o.get("date") %></td>
            <td><%= o.get("address") %></td>
            <td>
                <form action="updateOrder" method="post">
                    <input type="hidden" name="orderId"
                           value="<%= o.get("orderId") %>"/>
                    <select name="status">
                        <option>Placed</option>
                        <option>Preparing</option>
                        <option>Out for Delivery</option>
                        <option>Delivered</option>
                    </select>
                    <button type="submit" class="btn-sm">Update</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <hr/>
    <h2>Manage Users</h2>
    <table class="menu-table">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Current Type</th>
            <th>Action</th>
        </tr>
        <%
            List<User> users = new UserDAO().getAllUsers();
            for (User u : users) {
        %>
        <tr>
            <td><%= u.getUserId() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td>
                <span class="badge <%= u.getUserType().toLowerCase() %>">
                    <%= u.getUserType() %>
                </span>
            </td>
            <td>
                <form action="updateUserType" method="post">
                    <input type="hidden" name="userId"
                           value="<%= u.getUserId() %>"/>
                    <select name="userType">
                        <option <%= "Guest".equals(u.getUserType()) ? "selected" : "" %>>Guest</option>
                        <option <%= "Member".equals(u.getUserType()) ? "selected" : "" %>>Member</option>
                        <option <%= "Premium".equals(u.getUserType()) ? "selected" : "" %>>Premium</option>
                    </select>
                    <button type="submit" class="btn-sm">Update</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <hr/>
    <h2>Add New Menu Item</h2>
    <form action="addItem" method="post">
        <input type="text"   name="name"        placeholder="Item name"    required/>
        <input type="text"   name="description" placeholder="Description"  />
        <input type="number" name="price"        placeholder="Price"
               step="0.01" required/>
        <select name="category">
            <option>Veg</option>
            <option>Non-Veg</option>
            <option>Beverages</option>
            <option>Desserts</option>
        </select>
        <button type="submit" class="btn">Add Item</button>
    </form>
</div>
</body>
</html>