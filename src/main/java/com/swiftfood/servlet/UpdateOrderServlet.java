package com.swiftfood.servlet;

import com.swiftfood.dao.OrderDAO;
import com.swiftfood.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"admin".equals(user.getRole())) {
            res.sendRedirect("login.jsp");
            return;
        }
        int orderId   = Integer.parseInt(req.getParameter("orderId"));
        String status = req.getParameter("status");
        try {
            new OrderDAO().updateStatus(orderId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("admin.jsp");
    }
}