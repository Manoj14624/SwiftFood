<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.*, com.swiftfood.dao.*, com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Map<String, Object>> orders = new OrderDAO().getOrdersByUser(user.getUserId());
%>
<html>
<head>
    <title>My Orders - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span>SwiftFood</span>
    <span>Hello, <%= user.getUsername() %> |
        <a href="menu.jsp">Menu</a> |
        <a href="orderHistory.jsp">My Orders</a>  |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <h2>My Order History</h2>
    <% if (orders.isEmpty()) { %>
        <div class="empty-orders">
            <p>You have not placed any orders yet.</p>
            <a href="menu.jsp" class="btn">Order Now</a>
        </div>
    <% } else { %>
        <% for (Map<String, Object> order : orders) {
            int orderId = (Integer) order.get("orderId");
            List<Map<String, Object>> items =
                new OrderDAO().getOrderItems(orderId);
        %>
        <div class="order-card">
            <div class="order-card-header">
                <span>Order #<%= orderId %></span>
                <span><%= order.get("date") %></span>
                <span class="status-badge <%= order.get("status").toString().toLowerCase().replace(" ", "-") %>">
                    <%= order.get("status") %>
                </span>
            </div>
            <table class="bill-table">
                <tr>
                    <th>Item</th>
                    <th>Qty</th>
                    <th>Price</th>
                    <th>Amount</th>
                </tr>
                <% for (Map<String, Object> item : items) {
                    double amount = (Double)item.get("price") *
                                    (Integer)item.get("quantity");
                %>
                <tr>
                    <td><%= item.get("name") %></td>
                    <td><%= item.get("quantity") %></td>
                    <td>Rs. <%= item.get("price") %></td>
                    <td>Rs. <%= amount %></td>
                </tr>
                <% } %>
            </table>
            <div class="order-card-footer">
                <span>Delivery: <%= order.get("address") %></span>
                <span class="order-total">Total: Rs. <%= order.get("total") %></span>
            </div>
        </div>
        <% } %>
    <% } %>
</div>
</body>
</html>