package com.tia102g3.adminlogin.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.tia102g3.adminlogin.service.AdminLoginService;

@WebServlet("/adminLogin")
public class AdminLoginHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AdminLoginService adminLoginService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");

        String adminUsername = req.getParameter("adminUsername");
        String adminPassword = req.getParameter("adminPassword");

        if (!adminLoginService.validateAdmin(adminUsername, adminPassword)) {
            res.sendRedirect(req.getContextPath() + "/adminLogin?error=invalid");
        } else {
            HttpSession session = req.getSession();
            AdminLogin adminLogin = adminLoginService.findByAdminUsername(adminUsername);
            session.setAttribute("adminUsername", adminUsername);
            session.setAttribute("adminName", adminLogin.getAdminName()); // 確保設置 adminName

            String location = (String) session.getAttribute("location");
            if (location != null) {
                session.removeAttribute("location");
                res.sendRedirect(location);
            } else {
                res.sendRedirect(req.getContextPath() + "/admin");
            }
        }
    }
}
