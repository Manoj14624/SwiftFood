package com.swiftfood.servlet;

import com.swiftfood.dao.UserDAO;
import com.swiftfood.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = new UserDAO().loginUser(username, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);
                if ("admin".equals(user.getRole()))
                    res.sendRedirect("admin.jsp");
                else
                    res.sendRedirect("menu.jsp");
            } else {
                res.sendRedirect("login.jsp?error=Invalid+credentials");
            }
        } catch (Exception e) {
            res.sendRedirect("login.jsp?error=" + e.getMessage());
        }
    }
}