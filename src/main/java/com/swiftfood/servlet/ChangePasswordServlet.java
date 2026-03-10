package com.swiftfood.servlet;

import com.swiftfood.dao.UserDAO;
import com.swiftfood.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String currentPassword = req.getParameter("currentPassword");
        String newPassword     = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            res.sendRedirect("profile.jsp?error=New+passwords+do+not+match");
            return;
        }
        if (newPassword.length() < 6) {
            res.sendRedirect("profile.jsp?error=Password+must+be+at+least+6+characters");
            return;
        }
        try {
            boolean ok = new UserDAO().changePassword(
                    user.getUserId(), currentPassword, newPassword);
            if (ok) {
                user.setPassword(newPassword);
                session.setAttribute("loggedUser", user);
                res.sendRedirect("profile.jsp?msg=pwdchanged");
            } else {
                res.sendRedirect("profile.jsp?error=Current+password+is+incorrect");
            }
        } catch (Exception e) {
            res.sendRedirect("profile.jsp?error=" + e.getMessage());
        }
    }
}