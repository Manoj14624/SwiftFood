<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.*, com.swiftfood.dao.*, com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int orderId       = (Integer) session.getAttribute("lastOrderId");
    double total      = (Double)  session.getAttribute("lastOrderTotal");
    double discount   = (Double)  session.getAttribute("lastOrderDiscount");
    double deliveryFee= (Double)  session.getAttribute("lastOrderDeliveryFee");
    String address    = (String)  session.getAttribute("lastOrderAddress");
    double subtotal   = total - deliveryFee + discount;

    List<Map<String, Object>> items = new OrderDAO().getOrderItems(orderId);

    session.removeAttribute("lastOrderId");
    session.removeAttribute("lastOrderTotal");
    session.removeAttribute("lastOrderDiscount");
    session.removeAttribute("lastOrderDeliveryFee");
    session.removeAttribute("lastOrderAddress");
%>
<html>
<head>
    <title>Order Bill - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span>SwiftFood</span>
    <span>Hello, <%= user.getUsername() %> |
        <a href="orderHistory.jsp">My Orders</a> |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <div class="bill-card">
        <div class="bill-header">
            <h2>SwiftFood</h2>
            <p>Order Bill</p>
        </div>
        <div class="bill-info">
            <span>Order #<%= orderId %></span>
            <span>Customer: <%= user.getUsername() %></span>
            <span>Type: <%= user.getUserType() %></span>
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
        <div class="bill-summary">
            <div class="bill-row">
                <span>Subtotal</span>
                <span>Rs. <%= subtotal %></span>
            </div>
            <% if (discount > 0) { %>
            <div class="bill-row discount">
                <span>Discount (<%= user.getUserType() %>)</span>
                <span>- Rs. <%= discount %></span>
            </div>
            <% } %>
            <div class="bill-row">
                <span>Delivery Fee</span>
                <span><%= deliveryFee == 0 ? "Free" : "Rs. " + deliveryFee %></span>