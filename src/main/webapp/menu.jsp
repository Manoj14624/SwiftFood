<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.*, com.swiftfood.dao.*, com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Menu - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span>SwiftFood</span>
    <span>Hello, <%= user.getUsername() %> |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <h2>Our Menu</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="placeOrder" method="post">
        <table class="menu-table">
            <tr>
                <th>Item</th>
                <th>Category</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <%
                List<MenuItem> items = new MenuDAO().getAllItems();
                for (MenuItem m : items) {
            %>
            <tr>
                <td>
                    <%= m.getName() %>
                    <br/>
                    <small><%= m.getDescription() %></small>
                </td>
                <td><%= m.getCategory() %></td>
                <td>Rs. <%= m.getPrice() %></td>
                <td>
                    <input type="hidden" name="itemId" value="<%= m.getItemId() %>"/>
                    <input type="hidden" name="price"  value="<%= m.getPrice() %>"/>
                    <input type="number" name="qty" min="0" value="0" style="width:60px"/>
                </td>
            </tr>
            <% } %>
        </table>
        <br/>
        <label>Delivery Address:</label>
        <input type="text" name="address"
               value="<%= user.getAddress() != null ? user.getAddress() : "" %>"
               style="width:100%; padding:10px; margin-top:8px;"/>
        <br/><br/>
        <button type="submit" class="btn">Place Order</button>
    </form>
</div>
</body>
</html>