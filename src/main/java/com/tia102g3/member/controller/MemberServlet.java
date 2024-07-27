package com.tia102g3.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tia102g3.member.model.MemberService;
import com.tia102g3.member.model.MemberVO;

@WebServlet("/back-end/member/member.do")
@MultipartConfig
public class MemberServlet extends HttpServlet {

	Connection con;
	private byte[] personalPhotos;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("memberID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer memberID = null;
			try {
				memberID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memberID);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllmember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberID = Integer.valueOf(req.getParameter("memberID"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memberID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_member_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memberID = Integer.valueOf(req.getParameter("memberID").trim());

//			Part filePart = req.getPart("personalPhotos");
//			byte[] personalPhotos = null;
//
//			if (filePart != null && filePart.getSize() > 0) {
//				InputStream inputStream = filePart.getInputStream();
//				personalPhotos = inputStream.readAllBytes();
//			} else {
//				MemberService memberSvc = new MemberService();
//				MemberVO memberVO = memberSvc.getOneMember(memberID);
//				if (memberVO != null) {
//					personalPhotos = memberVO.getPersonalPhotos();
//				}
//			}

			Part filePart = req.getPart("personalPhotos");
	        if (filePart != null && filePart.getSize() > 0) {
	            try (InputStream inputStream = filePart.getInputStream()) {
	                byte[] personalPhotos = new byte[(int) filePart.getSize()];
	                int bytesRead = inputStream.read(personalPhotos);
	                if (bytesRead != personalPhotos.length) {
	                    errorMsgs.add("照片讀取不完整");
	                }

	            } catch (IOException e) {
	                errorMsgs.add("檔案讀取錯誤：" + e.getMessage());
	            }
	        } else {
	            errorMsgs.add("無照片");
	        }

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.getRequestDispatcher("/errorPage.jsp").forward(req, res);
			} else {

			}

			String name = req.getParameter("name").trim();
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!name.matches(nameReg)) {
				errorMsgs.add("姓名只能是中、英文字母、數字和_ ，且長度需在2到10之間");
			}

			String account = req.getParameter("account").trim();
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			String password = req.getParameter("password").trim();
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String email = req.getParameter("email").trim();
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("電子郵件請勿空白");
			}

			String gender = req.getParameter("gender").trim();
			if (gender == null || gender.trim().length() == 0) {
				errorMsgs.add("性別請勿空白");
			}

			String phone = req.getParameter("phone").trim();
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}

			String address = req.getParameter("address").trim();
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			java.sql.Date bD = null;
			try {
				bD = java.sql.Date.valueOf(req.getParameter("bD").trim());
			} catch (IllegalArgumentException e) {
				bD = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的日期!");
			}

			java.sql.Date regDate = null;
			try {
				regDate = java.sql.Date.valueOf(req.getParameter("regDate").trim());
			} catch (IllegalArgumentException e) {
				regDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的註冊日期!");
			}

			Integer coachMemberID = null;
			try {
				coachMemberID = Integer.valueOf(req.getParameter("coachMemberID").trim());
			} catch (NumberFormatException e) {
				coachMemberID = 0;
				errorMsgs.add("教練會員ID請填數字.");
			}

			String receiver = req.getParameter("receiver").trim();
			if (receiver == null || receiver.trim().length() == 0) {
				errorMsgs.add("收件人請勿空白");
			}

			String receiverAddress = req.getParameter("receiverAddress").trim();
			if (receiverAddress == null || receiverAddress.trim().length() == 0) {
				errorMsgs.add("收件地址請勿空白");
			}

			String receiverPhone = req.getParameter("receiverPhone").trim();
			if (receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.add("收件電話請勿空白");
			}

			String cardName = req.getParameter("cardName").trim();
			if (cardName == null || cardName.trim().length() == 0) {
				errorMsgs.add("卡片名稱請勿空白");
			}

			String cardValidTime = req.getParameter("cardValidTime").trim();
			if (cardValidTime == null || cardValidTime.trim().length() == 0) {
				errorMsgs.add("卡片有效期請勿空白");
			}

			String cardLast3No = req.getParameter("cardLast3No").trim();
			if (cardLast3No == null || cardLast3No.trim().length() == 0) {
				errorMsgs.add("卡片後三碼請勿空白");
			}

			String cardPhone = req.getParameter("cardPhone").trim();
			if (cardPhone == null || cardPhone.trim().length() == 0) {
				errorMsgs.add("卡片電話請勿空白");
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMemberID(memberID);
			memberVO.setPersonalPhotos(personalPhotos);
			memberVO.setName(name);
			memberVO.setAccount(account);
			memberVO.setPassword(password);
			memberVO.setEmail(email);
			memberVO.setGender(gender);
			memberVO.setPhone(phone);
			memberVO.setAddress(address);
			memberVO.setbD(bD);
			memberVO.setRegDate(regDate);
			memberVO.setCoachMemberID(coachMemberID);
			memberVO.setReceiver(receiver);
			memberVO.setReceiverAddress(receiverAddress);
			memberVO.setReceiverPhone(receiverPhone);
			memberVO.setCardName(cardName);
			memberVO.setCardValidTime(cardValidTime);
			memberVO.setCardLast3No(cardLast3No);
			memberVO.setCardPhone(cardPhone);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(memberID, personalPhotos, name, account, password, email, gender, phone,
					address, bD, regDate, coachMemberID, receiver, receiverAddress, receiverPhone, cardName, cardValidTime,
					cardLast3No, cardPhone);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Part filePart = req.getPart("personalPhotos");
			if (filePart != null && filePart.getSize() > 0) {
				try (InputStream inputStream = filePart.getInputStream()) {
					byte[] personalPhotos = new byte[(int) filePart.getSize()];
					int bytesRead = inputStream.read(personalPhotos);
					if (bytesRead != personalPhotos.length) {
						errorMsgs.add("照片讀取不完整");
					}

				} catch (IOException e) {
					errorMsgs.add("檔案讀取錯誤：" + e.getMessage());
				}
			} else {
				errorMsgs.add("無照片");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.getRequestDispatcher("/errorPage.jsp").forward(req, res);
			} else {

			}

			String name = req.getParameter("name").trim();
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!name.matches(nameReg)) {
				errorMsgs.add("姓名只能是中、英文字母、數字和_ ，且長度需在2到10之間");
			}

			String account = req.getParameter("account").trim();
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			String password = req.getParameter("password").trim();
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String email = req.getParameter("email").trim();
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("電子郵件請勿空白");
			}

			String gender = req.getParameter("gender").trim();
			if (gender == null || gender.trim().length() == 0) {
				errorMsgs.add("性別請勿空白");
			}

			String phone = req.getParameter("phone").trim();
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}

			String address = req.getParameter("address").trim();
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			java.sql.Date bD = null;
			try {
				bD = java.sql.Date.valueOf(req.getParameter("bD").trim());
			} catch (IllegalArgumentException e) {
				bD = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的日期!");
			}

			java.sql.Date regDate = null;
			try {
				regDate = java.sql.Date.valueOf(req.getParameter("regDate").trim());
			} catch (IllegalArgumentException e) {
				regDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的註冊日期!");
			}

			Integer coachMemberID = null;
			try {
				coachMemberID = Integer.valueOf(req.getParameter("coachMemberID").trim());
			} catch (NumberFormatException e) {
				coachMemberID = 0;
				errorMsgs.add("教練會員ID請填數字.");
			}

			String receiver = req.getParameter("receiver").trim();
			if (receiver == null || receiver.trim().length() == 0) {
				errorMsgs.add("收件人請勿空白");
			}

			String receiverAddress = req.getParameter("receiverAddress").trim();
			if (receiverAddress == null || receiverAddress.trim().length() == 0) {
				errorMsgs.add("收件地址請勿空白");
			}

			String receiverPhone = req.getParameter("receiverPhone").trim();
			if (receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.add("收件電話請勿空白");
			}

			String cardName = req.getParameter("cardName").trim();
			if (cardName == null || cardName.trim().length() == 0) {
				errorMsgs.add("卡片名稱請勿空白");
			}

			String cardValidTime = req.getParameter("cardValidTime").trim();
			if (cardValidTime == null || cardValidTime.trim().length() == 0) {
				errorMsgs.add("卡片有效期請勿空白");
			}

			String cardLast3No = req.getParameter("cardLast3No").trim();
			if (cardLast3No == null || cardLast3No.trim().length() == 0) {
				errorMsgs.add("卡片後三碼請勿空白");
			}

			String cardPhone = req.getParameter("cardPhone").trim();
			if (cardPhone == null || cardPhone.trim().length() == 0) {
				errorMsgs.add("卡片電話請勿空白");
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setPersonalPhotos(personalPhotos);
			memberVO.setName(name);
			memberVO.setAccount(account);
			memberVO.setPassword(password);
			memberVO.setEmail(email);
			memberVO.setGender(gender);
			memberVO.setPhone(phone);
			memberVO.setAddress(address);
			memberVO.setbD(bD);
			memberVO.setRegDate(regDate);
			memberVO.setCoachMemberID(coachMemberID);
			memberVO.setReceiver(receiver);
			memberVO.setReceiverAddress(receiverAddress);
			memberVO.setReceiverPhone(receiverPhone);
			memberVO.setCardName(cardName);
			memberVO.setCardValidTime(cardValidTime);
			memberVO.setCardLast3No(cardLast3No);
			memberVO.setCardPhone(cardPhone);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(personalPhotos, name, account, password, email, gender, phone, address, bD,
					regDate, coachMemberID, receiver, receiverAddress, receiverPhone, cardName, cardValidTime, cardLast3No,
					cardPhone);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllmember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memberID = Integer.valueOf(req.getParameter("memberID"));

			/*************************** 2.開始刪除資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMember(memberID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	public void init() throws ServletException {
		// JDBC setup here
	}
	
//	Object account = session.getAttribute("account");
//	if (account == null) {
//		session.setAttribute("location", req.getRequestURI());
//		res.sendRedirect(req.getContextPath() + "/login.html");
//		return;
//		// 有else可以省略if
//	} else {
//		chain.doFilter(request, response);
//	}
}
