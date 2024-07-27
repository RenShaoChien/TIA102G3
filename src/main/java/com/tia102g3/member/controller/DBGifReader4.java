package com.tia102g3.member.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
@WebServlet("/MemberDBGifReader4")
public class DBGifReader4 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String memberID = req.getParameter("memberID").trim();
			ResultSet rs = stmt.executeQuery(
				"select personalPhotos from tia102g3.member where memberID= "+memberID);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("personalPhotos"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/1.jpg"); // p110
                byte[] b = new byte[in.available()];
                in.read(b);
                out.write(b);
                in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/NoData/2.jpg"); // p110
//			byte[] b = new byte[in.available()];
//			in.read(b);
			byte[] b = in.readAllBytes();  // Java 9 ���s��k
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			// 獲取 JNDI 查詢的初始上下文
			Context ctx = new javax.naming.InitialContext();
			// 從 JNDI 命名服務查找資料來源
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G3");
			// 從資料來源獲取連接
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}