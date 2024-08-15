package com.tia102g3.member.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginFilter implements Filter {

	@Autowired
	MemberRepository memberRepository;

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

		// 確認請求是否需要驗證（例如所有 /member 路徑的請求）
		if ((requestURI.startsWith(req.getContextPath() + "/member")
				|| requestURI.startsWith(req.getContextPath() + "/coach_member"))
				&& (session == null || session.getAttribute("user") == null)) {

			// 保存當前請求的 URI，以便登入後可以重定向
			if (session != null) {
				session.setAttribute("location", requestURI);
			}

			// 重定向到登入頁面
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			// 如果已登入或請求不需要驗證，則繼續處理請求
			chain.doFilter(request, response);
		}
	}

	public void checkLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String username = cookie.getValue();
					// 根據 Cookie 中的用戶名查詢用戶並驗證身份
					Member member = memberRepository.findByAccount(username);
					if (member != null) {
						HttpSession session = request.getSession();
						session.setAttribute("user", member);
						session.setAttribute("loggedIn", true);
						break;
					}
				}
			}
		}
	}
}
