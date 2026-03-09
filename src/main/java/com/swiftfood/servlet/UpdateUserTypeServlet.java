package com.swiftfood.servlet;

import com.swiftfood.dao.UserDAO;
import com.swiftfood.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateUserType")
public class UpdateUserTypeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"admin".equals(user.getRole())) {
            res.sendRedirect("login.jsp");
            return;
        }
        int userId      = Integer.parseInt(req.getParameter("userId"));
        String userType = req.getParameter("userType");
        try {
            new UserDAO().updateUserType(userId, userType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("admin.jsp");
    }
}