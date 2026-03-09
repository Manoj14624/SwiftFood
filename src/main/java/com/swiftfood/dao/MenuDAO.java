package com.swiftfood.dao;

import com.swiftfood.model.MenuItem;
import java.sql.*;
import java.util.*;

public class MenuDAO {

    public List<MenuItem> getAllItems() throws SQLException {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE available='Y'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MenuItem m = new MenuItem();
                m.setItemId(rs.getInt("item_id"));
                m.setName(rs.getString("name"));
                m.setDescription(rs.getString("description"));
                m.setPrice(rs.getDouble("price"));
                m.setCategory(rs.getString("category"));
                items.add(m);
            }
        }
        return items;
    }

    public boolean addItem(MenuItem m) throws SQLException {
        String sql = "INSERT INTO menu_items (name, description, price, category)"
                + " VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getDescription());
            ps.setDouble(3, m.getPrice());
            ps.setString(4, m.getCategory());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteItem(int itemId) throws SQLException {
        String sql = "UPDATE menu_items SET available='N' WHERE item_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            return ps.executeUpdate() > 0;
        }
    }
}