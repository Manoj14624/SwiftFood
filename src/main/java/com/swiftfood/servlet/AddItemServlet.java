package com.swiftfood.servlet;

import com.swiftfood.dao.MenuDAO;
import com.swiftfood.model.MenuItem;
import com.swiftfood.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"admin".equals(user.getRole())) {
            res.sendRedirect("login.jsp");
            return;
        }
        MenuItem m = new MenuItem();
        m.setName(req.getParameter("name"));
        m.setDescription(req.getParameter("description"));
        m.setPrice(Double.parseDouble(req.getParameter("price")));
        m.setCategory(req.getParameter("category"));
        try {
            new MenuDAO().addItem(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("admin.jsp");
    }
}