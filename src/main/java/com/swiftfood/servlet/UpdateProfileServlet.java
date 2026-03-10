package com.swiftfood.servlet;

import com.swiftfood.dao.UserDAO;
import com.swiftfood.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String phone   = req.getParameter("phone");
        String address = req.getParameter("address");
        try {
            new UserDAO().updateProfile(user.getUserId(), phone, address);
            user.setPhone(phone);
            user.setAddress(address);
            session.setAttribute("loggedUser", user);
            res.sendRedirect("profile.jsp?msg=updated");
        } catch (Exception e) {
            res.sendRedirect("profile.jsp?error=" + e.getMessage());
        }
    }
}