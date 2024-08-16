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

import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.PasswordChangeDTO;
import com.tia102g3.member.service.MemberService;

@Controller
public class MemberPageController {

	@Autowired
	MemberService memberSvc;

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

	@GetMapping("modify_forgotten_password")
	public String modify_forgotten_password(Model model) {
		return "frontend/login/modify_forgotten_password";
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
	    
	    Member member = (Member) session.getAttribute("user");
	    if (member != null) {
	        Integer memberID = member.getMemberID();
	        Member memberDetails = memberSvc.findById(memberID);

	        if (memberDetails != null) {
	            model.addAttribute("member", memberDetails); // 添加 Member 對象到模型中
	            model.addAttribute("name", memberDetails.getName()); // 添加 name 屬性到模型中
	        }
	    }

	    return "frontend/member/account_information"; // 返回對應的視圖名稱
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
	public String historical_orders(Model model) {
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
	            // 確保這些屬性在 Member 類中存在並有值
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
	        @RequestParam(value = "personalPhotos", required = false) MultipartFile personalPhotos,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "gender", required = false) String gender,
	        @RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
	        @RequestParam(value = "phone", required = false) String phone,
	        @RequestParam(value = "address", required = false) String address,
	        @RequestParam(value = "receiver", required = false) String receiver,
	        @RequestParam(value = "receiverAddress", required = false) String receiverAddress,
	        @RequestParam(value = "receiverPhone", required = false) String receiverPhone,
	        @RequestParam(value = "cardName", required = false) String cardName,
	        @RequestParam(value = "cardNumber", required = false) String cardNumber,
	        @RequestParam(value = "cardValidTime", required = false) String cardValidTime,
	        @RequestParam(value = "cardLast3No", required = false) String cardLast3No,
	        @RequestParam(value = "cardPhone", required = false) String cardPhone,
	        @RequestParam(value = "oldPassword", required = false) String oldPassword,
	        @RequestParam(value = "newPassword", required = false) String newPassword,
	        @RequestParam(value = "newPasswordAgain", required = false) String newPasswordAgain,
	        @RequestParam("redirectPage") String redirectPage, HttpServletRequest request) {

	    HttpSession session = request.getSession();
	    Member member = (Member) session.getAttribute("user");

	    if (member != null) {
	        Integer memberID = member.getMemberID();
	        Member memberDetails = memberSvc.findById(memberID);

	        if (memberDetails != null) {
	            if ("change_password".equals(redirectPage)) {
	                // 比較明文密碼
	                if (!oldPassword.equals(memberDetails.getPassword())) {
	                    return "redirect:/change_password?error=incorrect_old_password";
	                }
	                if (!newPassword.equals(newPasswordAgain)) {
	                    return "redirect:/change_password?error=new_password_mismatch";
	                }
	                memberDetails.setPassword(newPassword);
	                
	            } else {
	                // 更新其他會員資料的邏輯
	                if (name != null)
	                    memberDetails.setName(name);
	                if (gender != null)
	                    memberDetails.setGender(gender);
	                if (birthday != null)
	                    memberDetails.setBD(Date.valueOf(birthday));
	                if (phone != null)
	                    memberDetails.setPhone(phone);
	                if (address != null)
	                    memberDetails.setAddress(address);
	                if (receiver != null)
	                    memberDetails.setReceiver(receiver);
	                if (receiverAddress != null)
	                    memberDetails.setReceiverAddress(receiverAddress);
	                if (receiverPhone != null)
	                    memberDetails.setReceiverPhone(receiverPhone);
	                if (cardName != null)
	                    memberDetails.setCardName(cardName);
	                if (cardNumber != null)
	                    memberDetails.setCardNumber(cardNumber);
	                if (cardValidTime != null)
	                    memberDetails.setCardValidTime(cardValidTime);
	                if (cardLast3No != null)
	                    memberDetails.setCardLast3No(cardLast3No);
	                if (cardPhone != null)
	                    memberDetails.setCardPhone(cardPhone);

	                // 處理檔案上傳
	                if (personalPhotos != null && !personalPhotos.isEmpty()) {
	                    try {
	                        memberDetails.setPersonalPhotos(personalPhotos.getBytes());
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }

	            memberSvc.updateMember(memberDetails);
	            session.setAttribute("user", memberDetails);
	        }
	    }

	    switch (redirectPage) {
	    case "account_information":
	        return "redirect:/account_information";
	    case "change_password":
	        return "redirect:/change_password";
	    case "receipt_information":
	        return "redirect:/receipt_information";
	    case "acc_information":
	        return "redirect:/acc_information";
	    default:
	        return "redirect:/account_information";
	    }
	}

}