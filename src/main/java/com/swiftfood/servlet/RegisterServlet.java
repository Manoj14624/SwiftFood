package com.swiftfood.servlet;

import com.swiftfood.dao.UserDAO;
import com.swiftfood.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        User u = new User();
        u.setUsername(req.getParameter("username"));
        u.setPassword(req.getParameter("password"));
        u.setEmail(req.getParameter("email"));
        u.setPhone(req.getParameter("phone"));
        u.setAddress(req.getParameter("address"));
        try {
            boolean ok = new UserDAO().registerUser(u);
            if (ok) res.sendRedirect("login.jsp?msg=registered");
            else    res.sendRedirect("register.jsp?error=1");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, res);
        }
    }
}