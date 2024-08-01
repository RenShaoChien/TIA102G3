package com.tia102g3.shoppingcart.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g3.shoppingcart.entity.ShoppingCartVO;
import com.tia102g3.shoppingcart.service.ShoppingCartService;

@WebServlet("/shoppingCart/shoppingCart.do")
public class ShoppingCartServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.print(123);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("shoppingCartID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入購物車編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shoppingCart/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer shoppingCartID = null;
				try {
					shoppingCartID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("購物車編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shoppingCart/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShoppingCartService scSvc = new ShoppingCartService();
				ShoppingCartVO sc = scSvc.findByPK(shoppingCartID);
				if (sc == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shoppingCart/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shoppingCartVO", sc); // 資料庫取出的empVO物件,存入req
				String url = "/shoppingCart/listOneShoppingCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer shoppingCartID = Integer.valueOf(req.getParameter("shoppingCartID"));
				
				/***************************2.開始查詢資料****************************************/
				ShoppingCartService scSvc = new ShoppingCartService();
				ShoppingCartVO sc = scSvc.findByPK(shoppingCartID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shoppingCartVO", sc);         // 資料庫取出的empVO物件,存入req
				String url = "/shoppingCart/update_shoppingCart_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer shoppingCartID = Integer.valueOf(req.getParameter("shoppingCartID").trim());

				Integer memberID = null;
				try {
					memberID = Integer.valueOf(req.getParameter("memberID").trim());
				} catch (NumberFormatException e) {
					memberID = 0;
					errorMsgs.add("會員編號請填數字.");
				}

				Integer productID = null;
				try {
					productID = Integer.valueOf(req.getParameter("productID").trim());
				} catch (NumberFormatException e) {
					productID = 0;
					errorMsgs.add("商品編號請填數字.");
				}
				
				Integer quantity = null;
				try {
					quantity = Integer.valueOf(req.getParameter("quantity").trim());
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("數量請填數字.");
				}
				

				

				ShoppingCartVO sc = new ShoppingCartVO();
				sc.setShoppingCartID(shoppingCartID);
				sc.setMemberID(memberID);
				sc.setProductID(productID);
				sc.setQuantity(quantity);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("shoppingCartVO", sc); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shoppingCart/update_shoppingCart_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShoppingCartService scSvc = new ShoppingCartService();
				sc = scSvc.updateSCart(shoppingCartID, memberID, productID, quantity);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shoppingCartVO", sc); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/shoppingCart/listOneShoppingCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer memberID = null;
				try {
memberID = Integer.valueOf(req.getParameter("memberID").trim());
				} catch (NumberFormatException e) {
					memberID = 0;
					errorMsgs.add("會員編號請填數字.");
				}
				
Integer productID = null;
				try {
					productID = Integer.valueOf(req.getParameter("productID").trim());
				} catch (NumberFormatException e) {
					productID = 0;
					errorMsgs.add("商品編號請填數字.");
				}
				
Integer quantity = null;
				try {
					quantity = Integer.valueOf(req.getParameter("quantity").trim());
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("數量請填數字.");
				}
				

				ShoppingCartVO sc = new ShoppingCartVO();
				sc.setMemberID(memberID);
				sc.setProductID(productID);
				sc.setQuantity(quantity);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("shoppingCartVO", sc); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shoppingCart/addShoppingCart.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ShoppingCartService scSvc = new ShoppingCartService();
				sc = scSvc.insertSCart(memberID, productID, quantity);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/shoppingCart/listAllShoppingCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer shoppingCartID = Integer.valueOf(req.getParameter("shoppingCartID"));
				
				/***************************2.開始刪除資料***************************************/
				ShoppingCartService scSvc = new ShoppingCartService();
				scSvc.deleteSCart(shoppingCartID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/shoppingCart/listAllShoppingCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
