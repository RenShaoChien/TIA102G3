package com.controllers.david.coachmember;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.member.model.PasswordChangeDTO;
import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.sportevent.service.SportEventServiceImpl;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.service.SystemCourseServiceImpl;

@Controller
public class CoachMemberPageController {

	@Autowired
	CoachMemberService coachMemberSvc;
	
	@Autowired
    SportEventServiceImpl seService;
	
    @Autowired
    SystemCourseServiceImpl scService;

	@GetMapping("/coach_member/coachHome_page")
	public String coachHome_page(Model model) {
		return "back-end/coach_member/coachHome_page";
	}

	@GetMapping("/coach_member/listAllCoachMember")
	public String listAllCoachMember(Model model) {
		return "back-end/coach_member/listAllCoachMember";
	}

	@ModelAttribute("coachMemberListData") // for select_page.html 第97 109行用 // for listAllMember.html 第85行用
	protected List<CoachMember> referenceListData2(Model model) {

		List<CoachMember> list = coachMemberSvc.getAll();
		return list;
	}

	// 前台教練會員註冊、登入頁面Controller
	@GetMapping("coach_verify_email")
	public String coach_verify_email() {
		return "frontend/coach_member/coach_verify_email";
	}
	
	@GetMapping("/coach_skill")
	public String coach_skill() {
	    return "frontend/coach_member/coach_skill"; // 返回教練技能頁面
	}

	@PostMapping("/coach_skill")
	public String processCoachSkill(Model model,
	                                @RequestParam("sportTypes") String sportTypes,
	                                @RequestParam("sportEventName") String sportEventName,
	                                @RequestParam("sportEquipment") String sportEquipment,
	                                @RequestParam("target-area") String targetArea,
	                                @RequestParam("courseLevel") String courseLevel,
	                                @RequestParam("loseWeight") String loseWeight,
	                                RedirectAttributes redirectAttributes) {
	    try {
	        SportEvent sportEvent = new SportEvent();
	        sportEvent.setSportTypes(sportTypes);
	        sportEvent.setSportEventName(sportEventName);
	        sportEvent.setSportEquipment(sportEquipment);

	        seService.addOne(sportEvent);

	        Random rd = new Random();
	        List<SystemCourse> customizedCourses = scService.getSystemCoursesByReqPara(sportTypes, sportEventName, sportEquipment, targetArea, courseLevel);
	        if (customizedCourses.size() > 0) {
	            SystemCourse randomCourse = customizedCourses.get(rd.nextInt(customizedCourses.size()));
	            model.addAttribute("systemCourse", randomCourse);
	            return "redirect:/coach_complete_page";
	        } else {
	            throw new IllegalArgumentException("目前沒有合適您的運動");
	        }
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("message", "目前沒有合適您的運動");
	        return "redirect:/coach_skill";
	    }
	}

	@GetMapping("/getEquipmentBySportEvent")
	@ResponseBody
	public Set<String> getEquipmentBySportEvent(@RequestParam String sportEventName) {
	    return seService.getEquipmentBySportEvent(sportEventName); // 根據運動項目獲取對應的運動器材
	}

	@GetMapping("coach_complete_page")
	public String coach_complete_page() {
		return "frontend/coach_member/coach_complete_page";
	}
	
	// 前台教練會員頁面Controller
	@GetMapping("/coach_account_information")
	public String coachAccountInformation(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    
	    if (session == null || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
	        return "redirect:/login?error=not_logged_in";
	    }
	    
	    // 獲取 CoachMember 物件
	    CoachMember coachMember = (CoachMember) session.getAttribute("coach");
	    
	    if (coachMember != null) {
	        Integer cMemberID = coachMember.getCMemberID();
	        CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
	        
	        if (coachMemberDetails != null) {
	        	model.addAttribute("coachMember", coachMemberDetails); // 添加 Member 對象到模型中
	            model.addAttribute("name", coachMemberDetails.getName()); // 添加 name 屬性到模型中
	        }
	    }

	    return "frontend/coach_member/coach_account_information"; // 返回對應的視圖名稱
	}
	
	@GetMapping("coach_account_skill")
	public String coach_account_skill(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("coach");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
                model.addAttribute("name", coachMemberDetails.getName());
            }
        }
		return "frontend/coach_member/coach_account_skill";
	}
	
	@GetMapping("/coach_change_password")
	public String coachChangePassword(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession();
	    CoachMember coachMember = (CoachMember) session.getAttribute("coach");

	    if (coachMember != null) {
	        Integer cMemberID = coachMember.getCMemberID();
	        CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);

	        if (coachMemberDetails != null) {
	            model.addAttribute("name", coachMemberDetails.getName());
	            model.addAttribute("coachMember", coachMemberDetails);
	            model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
	        }
	    }
	    return "frontend/coach_member/coach_change_password";
	}
	
	@GetMapping("coach_historical_orders")
	public String coach_historical_orders() {
		return "frontend/coach_member/coach_historical_orders";
	}
	
	@GetMapping("coach_receipt_information")
	public String coach_receipt_information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("coach");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
            	model.addAttribute("coachMember", coachMemberDetails);
                model.addAttribute("name", coachMemberDetails.getName());
                model.addAttribute("cardNumber", coachMemberDetails.getCardNumber());
            }
        }
		return "frontend/coach_member/coach_receipt_information";
	}
	
	@GetMapping("coach_acc_information")
	public String coach_acc_information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("coach");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
            	model.addAttribute("name", coachMemberDetails.getName());
            	model.addAttribute("cardNumber", coachMemberDetails.getCardNumber());
	            model.addAttribute("cardValidTime", coachMemberDetails.getCardValidTime() != null ? coachMemberDetails.getCardValidTime().toString() : ""); // 格式化為字符串
	            model.addAttribute("cardLast3No", coachMemberDetails.getCardLast3No());
	            model.addAttribute("cardName", coachMemberDetails.getCardName());
	            model.addAttribute("cardPhone", coachMemberDetails.getCardPhone());
            }
        }
		return "frontend/coach_member/coach_acc_information";
	}
	
	@GetMapping("coach_course_status")
	public String coach_course_status() {
		return "frontend/coach_member/coach_course_status";
	}
	
	@GetMapping("coach_course_products")
	public String coach_course_products() {
		return "frontend/coach_member/coach_course_products";
	}
	
	@GetMapping("/api/getCoachMemberDetails")
    @ResponseBody
    public CoachMember getCoachMemberDetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("coach");
        

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);

            return coachMemberDetails; // 返回會員詳細信息作為 JSON 響應
        }
        return null;
    }
	
	@PostMapping("/submitCoach")
	public String submitCoach(
	        @RequestParam(value = "personalPhotos", required = false) MultipartFile personalPhotos, // 上傳的個人照片
	        @RequestParam(value = "name", required = false) String name, // 教練姓名
	        @RequestParam(value = "gender", required = false) String gender, // 教練性別
	        @RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday, // 教練生日，格式為 yyyy-MM-dd
	        @RequestParam(value = "phone", required = false) String phone, // 教練電話
	        @RequestParam(value = "address", required = false) String address, // 教練地址
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
	    CoachMember coachMember = (CoachMember) session.getAttribute("coach"); // 從 Session 中取得當前教練

	    if (coachMember != null) { // 如果教練存在
	        Integer cMemberID = coachMember.getCMemberID(); // 取得教練 ID
	        CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID); // 根據教練 ID 查找教練詳細資料

	        if (coachMemberDetails != null) { // 如果找到教練資料
	            if ("coach_change_password".equals(redirectPage)) { // 如果重定向頁面是修改密碼
	                // 比較明文密碼
	                if (!oldPassword.equals(coachMemberDetails.getPassword())) { // 檢查舊密碼是否正確
	                    return "redirect:/coach_change_password?error=incorrect_old_password"; // 如果不正確，重定向到修改密碼頁面並附加錯誤參數
	                }
	                if (!newPassword.equals(newPasswordAgain)) { // 檢查新密碼和確認新密碼是否一致
	                    return "redirect:/coach_change_password?error=new_password_mismatch"; // 如果不一致，重定向到修改密碼頁面並附加錯誤參數
	                }
	                coachMemberDetails.setPassword(newPassword); // 設定新密碼
	                
	            } else { // 如果重定向頁面不是修改密碼
	                // 更新其他教練資料的邏輯
	                if (name != null)
	                    coachMemberDetails.setName(name); // 更新姓名
	                if (gender != null)
	                    coachMemberDetails.setGender(gender); // 更新性別
	                if (birthday != null)
	                    coachMemberDetails.setBD(Date.valueOf(birthday)); // 更新生日
	                if (phone != null)
	                    coachMemberDetails.setPhone(phone); // 更新電話
	                if (address != null)
	                    coachMemberDetails.setAddress(address); // 更新地址
	                if (receiver != null)
	                    coachMemberDetails.setReceiver(receiver); // 更新收件人姓名
	                if (receiverAddress != null)
	                    coachMemberDetails.setReceiverAddress(receiverAddress); // 更新收件人地址
	                if (receiverPhone != null)
	                    coachMemberDetails.setReceiverPhone(receiverPhone); // 更新收件人電話
	                if (cardName != null)
	                    coachMemberDetails.setCardName(cardName); // 更新信用卡持卡人姓名
	                if (cardNumber != null)
	                    coachMemberDetails.setCardNumber(cardNumber); // 更新信用卡號
	                if (cardValidTime != null)
	                    coachMemberDetails.setCardValidTime(cardValidTime); // 更新信用卡有效期限
	                if (cardLast3No != null)
	                    coachMemberDetails.setCardLast3No(cardLast3No); // 更新信用卡最後三碼
	                if (cardPhone != null)
	                    coachMemberDetails.setCardPhone(cardPhone); // 更新信用卡聯絡電話

	                // 處理檔案上傳
	                if (personalPhotos != null && !personalPhotos.isEmpty()) { // 如果有上傳照片且不為空
	                    try {
	                        coachMemberDetails.setPersonalPhotos(personalPhotos.getBytes()); // 設定教練照片
	                    } catch (IOException e) {
	                        e.printStackTrace(); // 處理檔案讀取錯誤
	                    }
	                }
	            }

	            coachMemberSvc.updateCoachMember(coachMemberDetails); // 更新教練資料到資料庫
	            session.setAttribute("coach", coachMemberDetails); // 更新 Session 中的教練資料
	        }
	    }

	    // 根據重定向頁面參數返回相應的頁面
	    switch (redirectPage) {
	        case "coach_account_information":
	            return "redirect:/coach_account_information"; // 重定向到教練資訊頁面
	        case "coach_change_password":
	            return "redirect:/coach_change_password"; // 重定向到修改密碼頁面
	        case "coach_receipt_information":
	            return "redirect:/coach_receipt_information"; // 重定向到收據資訊頁面
	        case "coach_acc_information":
	            return "redirect:/coach_acc_information"; // 重定向到其他帳號資訊頁面
	        default:
	            return "redirect:/coach_account_information"; // 預設重定向到教練資訊頁面
	    }
	}

}
