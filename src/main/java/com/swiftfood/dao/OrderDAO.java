package com.swiftfood.dao;

import java.sql.*;
import java.util.*;

public class OrderDAO {

    public int placeOrder(int userId, double total, String address)
            throws SQLException {
        String sql = "INSERT INTO orders (user_id, total_price, delivery_addr)"
                + " VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql,
                     new String[]{"order_id"})) {
            ps.setInt(1, userId);
            ps.setDouble(2, total);
            ps.setString(3, address);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public void addOrderItem(int orderId, int itemId,
                             int qty, double price) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, item_id, quantity, price)"
                + " VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, itemId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.executeUpdate();
        }
    }

    public List<Map<String, Object>> getAllOrders() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT o.order_id, u.username, o.total_price,"
                + " o.status, o.order_date, o.delivery_addr"
                + " FROM orders o JOIN users u ON o.user_id=u.user_id"
                + " ORDER BY o.order_date DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("orderId", rs.getInt("order_id"));
                row.put("username", rs.getString("username"));
                row.put("total", rs.getDouble("total_price"));
                row.put("status", rs.getString("status"));
                row.put("date", rs.getString("order_date"));
                row.put("address", rs.getString("delivery_addr"));
                list.add(row);
            }
        }
        return list;
    }

    public boolean updateStatus(int orderId, String status)
            throws SQLException {
        String sql = "UPDATE orders SET status=? WHERE order_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> getOrdersByUser(int userId)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT o.order_id, o.total_price, o.status,"
                + " o.order_date, o.delivery_addr"
                + " FROM orders o WHERE o.user_id = ?"
                + " ORDER BY o.order_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("orderId",  rs.getInt("order_id"));
                row.put("total",    rs.getDouble("total_price"));
                row.put("status",   rs.getString("status"));
                row.put("date",     rs.getString("order_date"));
                row.put("address",  rs.getString("delivery_addr"));
                list.add(row);
            }
        }
        return list;
    }

    public List<Map<String, Object>> getOrderItems(int orderId)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT m.name, oi.quantity, oi.price"
                + " FROM order_items oi"
                + " JOIN menu_items m ON oi.item_id = m.item_id"
                + " WHERE oi.order_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("name",     rs.getString("name"));
                row.put("quantity", rs.getInt("quantity"));
                row.put("price",    rs.getDouble("price"));
                list.add(row);
            }
        }
        return list;
    }
}