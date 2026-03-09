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
        try {
            OrderDAO dao = new OrderDAO();
            int orderId = dao.placeOrder(user.getUserId(), total, address);
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