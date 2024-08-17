package com.controllers.david.member;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.service.CourseOrderService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.PasswordChangeDTO;
import com.tia102g3.member.service.MemberService;

@Controller
public class MemberPageController {

	@Autowired
	MemberService memberSvc;
	
	@Autowired
	CoachMemberService coachMemberSvc;
	
	@Autowired
	CourseOrderService courseOrderSvc;

	@GetMapping("/member/member_courseOrder_index")
	public String member_courseOrder_index(Model model) {
		return "member_courseOrder_index";
	}

	@GetMapping("/member/select_page")
	public String select_page(Model model) {
		return "back-end/member/select_page";
	}

	@GetMapping("/member/listAllMember")
	public String listAllMember(Model model) {
		return "back-end/member/listAllMember";
	}

	@ModelAttribute("memberListData") // for select_page.html 第97 109行用 // for listAllMember.html 第85行用
	protected List<Member> referenceListData(Model model) {

		List<Member> list = memberSvc.getAll();
		return list;
	}

	// 前台會員註冊、登入頁面Controller
	@GetMapping("login")
	public String login(Model model) {
		return "frontend/login/login";
	}

	@GetMapping("register")
	public String register(Model model) {
		return "frontend/login/register";
	}

	@GetMapping("verify_email")
	public String verify_email() {
		return "frontend/login/verify_email";
	}

	@GetMapping("complete_page")
	public String complete_page() {
		return "frontend/login/complete_page";
	}

	// 前台忘記密碼頁面Controller
	@GetMapping("password_verify_email")
	public String password_verify_email(Model model) {
		return "frontend/login/password_verify_email";
	}

	/**
     * 顯示忘記密碼頁面
     * @param request HttpServletRequest
     * @param model Model
     * @return 忘記密碼頁面的視圖名稱
     */
    @GetMapping("/modify_forgotten_password")
    public String modifyForgottenPassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(); // 取得 HttpSession 物件
        Member member = (Member) session.getAttribute("user"); // 從 session 中取得當前會員對象

        if (member != null) { // 如果會員存在
            Integer memberID = member.getMemberID(); // 取得會員 ID
            Member memberDetails = memberSvc.findById(memberID); // 從資料庫中取得會員詳細資料

            if (memberDetails != null) { // 如果會員詳細資料存在
                model.addAttribute("member", memberDetails); // 將會員詳細資料添加到模型中
                model.addAttribute("name", memberDetails.getName()); // 將會員姓名添加到模型中
                model.addAttribute("passwordChangeDTO", new PasswordChangeDTO()); // 創建一個新的 PasswordChangeDTO 物件並添加到模型中
            }
        } else {
            model.addAttribute("passwordChangeDTO", new PasswordChangeDTO()); // 如果會員不存在，仍然創建 PasswordChangeDTO 物件
        }

        return "frontend/login/modify_forgotten_password"; // 返回視圖名稱，顯示忘記密碼頁面
    }

    /**
     * 處理忘記密碼的表單提交
     * @param passwordChangeDTO 包含新密碼和確認新密碼的數據傳輸對象
     * @param request HttpServletRequest
     * @param model Model
     * @return 重定向到相應的頁面或顯示錯誤消息
     */
    @PostMapping("/modify_forgotten_password")
    public String modifyForgottenPassword(@ModelAttribute("passwordChangeDTO") PasswordChangeDTO passwordChangeDTO,
                                          HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(); // 取得 HttpSession 物件
        Integer memberID = (Integer) session.getAttribute("verifiedMemberID"); // 從 session 中取得驗證過的會員 ID

        if (memberID != null) { // 如果會員 ID 存在
            Member memberDetails = memberSvc.findById(memberID); // 從資料庫中取得會員詳細資料

            if (memberDetails != null) { // 如果會員詳細資料存在
                // 檢查新密碼和確認新密碼是否一致
                if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getNewPasswordAgain())) {
                    model.addAttribute("errorMessage", "新密碼與確認新密碼不一致"); // 添加錯誤消息到模型中
                    return "frontend/login/modify_forgotten_password"; // 返回到修改密碼頁面
                }

                // 設定新密碼
                memberDetails.setPassword(passwordChangeDTO.getNewPasswordAgain()); // 設定會員的新密碼
                
                try {
                    // 更新資料庫
                    memberSvc.updateMember(memberDetails); // 更新會員資料到資料庫
                    
                    // 清除 session 中的驗證資訊
                    session.removeAttribute("verifiedMemberID"); // 移除 session 中的驗證會員 ID
                    session.removeAttribute("verificationCode"); // 移除 session 中的驗證碼
                    
                    // 重定向到完成頁面
                    return "redirect:/password_complete_page"; // 重定向到密碼修改完成頁面
                } catch (Exception e) {
                    // 處理資料庫更新錯誤
                    model.addAttribute("errorMessage", "密碼更新失敗，請稍後再試"); // 添加錯誤消息到模型中
                    return "frontend/login/modify_forgotten_password"; // 返回到修改密碼頁面
                }
            }
        }

        // 如果沒有找到會員 ID 或會員資料
        model.addAttribute("errorMessage", "無效的會員ID或會話已過期"); // 添加錯誤消息到模型中
        return "frontend/login/modify_forgotten_password"; // 返回到修改密碼頁面
    }
    
    
    
    
	
	@GetMapping("password_complete_page")
	public String password_complete_page(Model model) {
		return "frontend/login/password_complete_page";
	}

	// 前台會員頁面Controller
	@GetMapping("/account_information")
	public String accountInformation(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    
	    // 檢查是否登入
	    if (session == null || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
	        return "redirect:/login?error=not_logged_in"; // 重定向到登入頁面，帶上錯誤參數
	    }
	    
	    Object user = session.getAttribute("user");
	    Object coach = session.getAttribute("coach");
	    
	    if (user instanceof Member) { // 如果是一般會員
	        Member member = (Member) user;
	        Integer memberID = member.getMemberID();
	        Member memberDetails = memberSvc.findById(memberID);

	        if (memberDetails != null) {
	            model.addAttribute("member", memberDetails); // 添加 Member 對象到模型中
	            model.addAttribute("name", memberDetails.getName()); // 添加 name 屬性到模型中
	        }

	        return "frontend/member/account_information"; // 返回一般會員的視圖名稱

	    } else if (coach instanceof CoachMember) { // 如果是教練會員
	        CoachMember coachMember = (CoachMember) coach;
	        Integer coachMemberID = coachMember.getCMemberID();
	        CoachMember coachMemberDetails = coachMemberSvc.findById(coachMemberID);

	        if (coachMemberDetails != null) {
	            model.addAttribute("coachMember", coachMemberDetails); // 添加 CoachMember 對象到模型中
	            model.addAttribute("name", coachMemberDetails.getName()); // 添加 name 屬性到模型中
	        }

	        return "frontend/coach_member/coach_account_information"; // 返回教練會員的視圖名稱
	    }

	    // 如果用戶不屬於上述兩種情況，重定向到登入頁面
	    return "redirect:/login?error=unknown_user_type";
	}

	@GetMapping("change_password")
	public String change_password(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession();
	    Member member = (Member) session.getAttribute("user");

	    if (member != null) {
	        Integer memberID = member.getMemberID();
	        Member memberDetails = memberSvc.findById(memberID);

	        if (memberDetails != null) {
	            model.addAttribute("member", memberDetails);
	            model.addAttribute("name", memberDetails.getName());
	            model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
	        }
	    }
	    return "frontend/member/change_password";
	}

	@GetMapping("historical_orders")
	public String historicalOrders(Model model) {
	    // 从服务层获取所有课程订单数据
	    List<CourseOrder> list = courseOrderSvc.getAll();
	    model.addAttribute("courseOrderListData", list);
	    return "frontend/member/historical_orders";
	}

	@GetMapping("receipt_information")
	public String receipt_information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");

		if (member != null) {
			Integer memberID = member.getMemberID();
			Member memberDetails = memberSvc.findById(memberID);

			if (memberDetails != null) {
				model.addAttribute("member", memberDetails); // 添加 Member 對象到模型中
				model.addAttribute("name", memberDetails.getName()); // 添加 name 屬性到模型中
				model.addAttribute("cardNumber", memberDetails.getCardNumber());
			}
		}
		return "frontend/member/receipt_information";
	}

	@GetMapping("/acc_information")
	public String accInformation(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession();
	    Member member = (Member) session.getAttribute("user");

	    if (member != null) {
	        Integer memberID = member.getMemberID();
	        Member memberDetails = memberSvc.findById(memberID);

	        if (memberDetails != null) {
	        	model.addAttribute("name", memberDetails.getName());
	            model.addAttribute("cardNumber", memberDetails.getCardNumber());
	            model.addAttribute("cardValidTime", memberDetails.getCardValidTime() != null ? memberDetails.getCardValidTime().toString() : ""); // 格式化為字符串
	            model.addAttribute("cardLast3No", memberDetails.getCardLast3No());
	            model.addAttribute("cardName", memberDetails.getCardName());
	            model.addAttribute("cardPhone", memberDetails.getCardPhone());
	        }
	    }

	    return "frontend/member/acc_information";
	}
	
	@GetMapping("/api/getMemberDetails")
    @ResponseBody
    public Member getMemberDetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("user");
        

        if (member != null) {
            Integer memberID = member.getMemberID();
            Member memberDetails = memberSvc.findById(memberID);

            return memberDetails; // 返回會員詳細信息作為 JSON 響應
        }
        return null;
    }

	@PostMapping("/submit")
	public String submitAccountInformation(
	        @RequestParam(value = "personalPhotos", required = false) MultipartFile personalPhotos, // 上傳的個人照片
	        @RequestParam(value = "name", required = false) String name, // 會員姓名
	        @RequestParam(value = "gender", required = false) String gender, // 會員性別
	        @RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday, // 會員生日，格式為 yyyy-MM-dd
	        @RequestParam(value = "phone", required = false) String phone, // 會員電話
	        @RequestParam(value = "address", required = false) String address, // 會員地址
	        @RequestParam(value = "receiver", required = false) String receiver, // 收件人姓名
	        @RequestParam(value = "receiverAddress", required = false) String receiverAddress, // 收件人地址
	        @RequestParam(value = "receiverPhone", required = false) String receiverPhone, // 收件人電話
	        @RequestParam(value = "cardName", required = false) String cardName, // 信用卡持卡人姓名
	        @RequestParam(value = "cardNumber", required = false) String cardNumber, // 信用卡號
	        @RequestParam(value = "cardValidTime", required = false) String cardValidTime, // 信用卡有效期限
	        @RequestParam(value = "cardLast3No", required = false) String cardLast3No, // 信用卡最後三碼
	        @RequestParam(value = "cardPhone", required = false) String cardPhone, // 信用卡聯絡電話
	        @RequestParam(value = "oldPassword", required = false) String oldPassword, // 舊密碼
	        @RequestParam(value = "newPassword", required = false) String newPassword, // 新密碼
	        @RequestParam(value = "newPasswordAgain", required = false) String newPasswordAgain, // 確認新密碼
	        @RequestParam("redirectPage") String redirectPage, // 需要重定向的頁面
	        HttpServletRequest request) {

	    HttpSession session = request.getSession(); // 取得 HTTP Session
	    Member member = (Member) session.getAttribute("user"); // 從 Session 中取得當前會員

	    if (member != null) { // 如果會員存在
	        Integer memberID = member.getMemberID(); // 取得會員 ID
	        Member memberDetails = memberSvc.findById(memberID); // 根據會員 ID 查找會員詳細資料

	        if (memberDetails != null) { // 如果找到會員資料
	            if ("change_password".equals(redirectPage)) { // 如果重定向頁面是修改密碼
	                // 比較明文密碼
	                if (!oldPassword.equals(memberDetails.getPassword())) { // 檢查舊密碼是否正確
	                    return "redirect:/change_password?error=incorrect_old_password"; // 如果不正確，重定向到修改密碼頁面並附加錯誤參數
	                }
	                if (!newPassword.equals(newPasswordAgain)) { // 檢查新密碼和確認新密碼是否一致
	                    return "redirect:/change_password?error=new_password_mismatch"; // 如果不一致，重定向到修改密碼頁面並附加錯誤參數
	                }
	                memberDetails.setPassword(newPassword); // 設定新密碼
	                
	            } else { // 如果重定向頁面不是修改密碼
	                // 更新其他會員資料的邏輯
	                if (name != null)
	                    memberDetails.setName(name); // 更新姓名
	                if (gender != null)
	                    memberDetails.setGender(gender); // 更新性別
	                if (birthday != null)
	                    memberDetails.setBD(Date.valueOf(birthday)); // 更新生日
	                if (phone != null)
	                    memberDetails.setPhone(phone); // 更新電話
	                if (address != null)
	                    memberDetails.setAddress(address); // 更新地址
	                if (receiver != null)
	                    memberDetails.setReceiver(receiver); // 更新收件人姓名
	                if (receiverAddress != null)
	                    memberDetails.setReceiverAddress(receiverAddress); // 更新收件人地址
	                if (receiverPhone != null)
	                    memberDetails.setReceiverPhone(receiverPhone); // 更新收件人電話
	                if (cardName != null)
	                    memberDetails.setCardName(cardName); // 更新信用卡持卡人姓名
	                if (cardNumber != null)
	                    memberDetails.setCardNumber(cardNumber); // 更新信用卡號
	                if (cardValidTime != null)
	                    memberDetails.setCardValidTime(cardValidTime); // 更新信用卡有效期限
	                if (cardLast3No != null)
	                    memberDetails.setCardLast3No(cardLast3No); // 更新信用卡最後三碼
	                if (cardPhone != null)
	                    memberDetails.setCardPhone(cardPhone); // 更新信用卡聯絡電話

	                // 處理檔案上傳
	                if (personalPhotos != null && !personalPhotos.isEmpty()) { // 如果有上傳照片且不為空
	                    try {
	                        memberDetails.setPersonalPhotos(personalPhotos.getBytes()); // 設定會員照片
	                    } catch (IOException e) {
	                        e.printStackTrace(); // 處理檔案讀取錯誤
	                    }
	                }
	            }

	            memberSvc.updateMember(memberDetails); // 更新會員資料到資料庫
	            session.setAttribute("user", memberDetails); // 更新 Session 中的會員資料
	        }
	    }

	    // 根據重定向頁面參數返回相應的頁面
	    switch (redirectPage) {
	        case "account_information":
	            return "redirect:/account_information"; // 重定向到帳號資訊頁面
	        case "change_password":
	            return "redirect:/change_password"; // 重定向到修改密碼頁面
	        case "receipt_information":
	            return "redirect:/receipt_information"; // 重定向到收據資訊頁面
	        case "acc_information":
	            return "redirect:/acc_information"; // 重定向到其他帳號資訊頁面
	        default:
	            return "redirect:/account_information"; // 預設重定向到帳號資訊頁面
	    }
	}


}