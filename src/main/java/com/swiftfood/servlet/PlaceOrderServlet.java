package com.swiftfood.servlet;

import com.swiftfood.dao.OrderDAO;
import com.swiftfood.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String[] itemIds = req.getParameterValues("itemId");
        String[] qtys    = req.getParameterValues("qty");
        String[] prices  = req.getParameterValues("price");
        String address   = req.getParameter("address");
        double total = 0;
        for (int i = 0; i < prices.length; i++)
            total += Double.parseDouble(prices[i]) * Integer.parseInt(qtys[i]);

        String userType = user.getUserType();
        if (userType == null) userType = "Guest";

        double discount = 0;
        if ("Member".equals(userType))        discount = total * 0.10;
        else if ("Premium".equals(userType))  discount = total * 0.20;

        double deliveryFee = 0;
        if ("Guest".equals(userType)) deliveryFee = 30.0;

        double finalTotal = total - discount + deliveryFee;
        try {
            OrderDAO dao = new OrderDAO();
            int orderId = dao.placeOrder(user.getUserId(), finalTotal, address);
            for (int i = 0; i < itemIds.length; i++)
                dao.addOrderItem(orderId,
                        Integer.parseInt(itemIds[i]),
                        Integer.parseInt(qtys[i]),
                        Double.parseDouble(prices[i]));
            res.sendRedirect("orderSuccess.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("menu.jsp").forward(req, res);
        }
    }
}