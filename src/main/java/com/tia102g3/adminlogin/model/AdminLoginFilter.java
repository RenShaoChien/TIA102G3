package com.tia102g3.adminlogin.model;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化過濾器
    }

    @Override
    public void destroy() {
        // 銷毀過濾器
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // 獲取 session
        HttpSession session = req.getSession(false);
        
        // 獲取請求的 URI
        String requestURI = req.getRequestURI();
        
        // 確認請求是否需要驗證（例如所有 /admin 路徑的請求）
        if (requestURI.startsWith(req.getContextPath() + "/admin") && 
            (session == null || session.getAttribute("adminUsername") == null)) {
            
            // 保存當前請求的 URI，以便登入後可以重定向
            if (session != null) {
                session.setAttribute("location", requestURI);
            }
            
            // 重定向到登入頁面
            res.sendRedirect(req.getContextPath() + "/adminLogin");
        } else {
            // 如果已登入或請求不需要驗證，則繼續處理請求
            chain.doFilter(request, response);
        }
    }
}
