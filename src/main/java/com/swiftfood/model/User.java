package com.swiftfood.model;

public class User {
    private int userId;
    private String username, password, email, phone, address, role;

    public int getUserId() { return userId; }
    public void setUserId(int id) { this.userId = id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getPhone() { return phone; }
    public void setPhone(String p) { this.phone = p; }
    public String getAddress() { return address; }
    public void setAddress(String a) { this.address = a; }
    public String getRole() { return role; }
    public void setRole(String r) { this.role = r; }
}