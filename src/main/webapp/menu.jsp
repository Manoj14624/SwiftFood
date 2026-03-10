<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.*, com.swiftfood.dao.*, com.swiftfood.model.*" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String userType = user.getUserType();
    if (userType == null) userType = "Guest";
    double discountPct = 0;
    double deliveryFee = 30.0;
    if ("Member".equals(userType)) {
        discountPct = 10;
        deliveryFee = 0;
    } else if ("Premium".equals(userType)) {
        discountPct = 20;
        deliveryFee = 0;
    }
    List<MenuItem> items = new MenuDAO().getAllItems();
%>
<html>
<head>
    <title>Menu - SwiftFood</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <span class="brand">Swift<span>Food</span></span>
    <span class="user-info">
        Hello, <%= user.getUsername() %> |
        <a href="profile.jsp">👤 Profile</a> |
        <a href="orderHistory.jsp">📋 My Orders</a> |
        <a href="logout">Logout</a>
    </span>
</div>
<div class="container">
    <div class="user-type-banner <%= userType.toLowerCase() %>">
        <span>🎖️ Membership: <strong><%= userType %></strong></span>
        <span>🏷️ Discount: <strong><%= (int)discountPct %>%</strong></span>
        <span>🚚 Delivery: <strong><%= deliveryFee == 0 ? "Free" : "Rs. " + (int)deliveryFee %></strong></span>
    </div>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="placeOrder" method="post">
        <div class="category-filters">
            <button type="button" class="filter-btn active"
                    onclick="filterMenu('all', this)">🍽️ All</button>
            <button type="button" class="filter-btn"
                    onclick="filterMenu('veg', this)">🥦 Veg</button>
            <button type="button" class="filter-btn"
                    onclick="filterMenu('non-veg', this)">🍗 Non-Veg</button>
            <button type="button" class="filter-btn"
                    onclick="filterMenu('beverages', this)">🥤 Beverages</button>
            <button type="button" class="filter-btn"
                    onclick="filterMenu('desserts', this)">🍰 Desserts</button>
        </div>
        <div class="menu-grid" id="menuGrid">
            <% for (MenuItem m : items) {
                String cat = m.getCategory().toLowerCase().replace(" ", "-");
                String emoji = "🍽️";
                if ("Veg".equals(m.getCategory()))           emoji = "🥗";
                if ("Non-Veg".equals(m.getCategory()))       emoji = "🍗";
                if ("Beverages".equals(m.getCategory()))     emoji = "🥤";
                if ("Desserts".equals(m.getCategory()))      emoji = "🍰";
            %>
            <div class="menu-card" data-category="<%= cat %>">
                <span class="menu-card-emoji"><%= emoji %></span>
                <div class="menu-card-name"><%= m.getName() %></div>
                <div class="menu-card-desc"><%= m.getDescription() %></div>
                <div class="menu-card-footer">
                    <span class="menu-card-price">Rs. <%= m.getPrice() %></span>
                    <span class="menu-card-category cat-<%= cat %>">
                        <%= m.getCategory() %>
                    </span>
                </div>
                <div style="margin-top:12px;">
                    <input type="hidden" name="itemId" value="<%= m.getItemId() %>"/>
                    <input type="hidden" name="price"  value="<%= m.getPrice() %>"/>
                    <input type="number" name="qty" min="0" value="0"
                           class="qty-input"/>
                </div>
            </div>
            <% } %>
        </div>
        <div class="order-summary-bar">
            <div style="flex:1;">
                <label style="font-weight:700; color:#1A1A2E; display:block; margin-bottom:6px;">
                    📍 Delivery Address
                </label>
                <input type="text" name="address"
                       value="<%= user.getAddress() != null ? user.getAddress() : "" %>"
                       placeholder="Enter your delivery address"/>
            </div>
            <div style="text-align:right; min-width:200px;">
                <% if (discountPct > 0) { %>
                <p style="color:#27AE60; font-size:13px; margin-bottom:4px;">
                    ✅ <%= (int)discountPct %>% <%= userType %> discount applied
                </p>
                <% } %>
                <p style="color:#666; font-size:13px; margin-bottom:8px;">
                    🚚 Delivery fee:
                    <strong><%= deliveryFee == 0 ? "Free" : "Rs. " + (int)deliveryFee %></strong>
                </p>
                <button type="submit" class="btn">🛒 Place Order</button>
            </div>
        </div>
    </form>
</div>
<script>
function filterMenu(category, btn) {
    document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    document.querySelectorAll('.menu-card').forEach(card => {
        if (category === 'all' || card.dataset.category === category) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}
</script>
</body>
</html>