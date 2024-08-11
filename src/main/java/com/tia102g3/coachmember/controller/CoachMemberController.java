package com.tia102g3.coachmember.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;

@Controller
@RequestMapping("/coach_member")
public class CoachMemberController {

	@Autowired
	CoachMemberService coachMemberSvc; // 注入CoachMemberService

	/*
	 * 顯示添加員工的頁面
	 */
	@GetMapping("addCoachMember")
	public String addCoachMember(ModelMap model) {
		CoachMember coachMember = new CoachMember();
		model.addAttribute("coachMember", coachMember); // 綁定空的CoachMember到模型
		return "back-end/coach_member/addCoachMember"; // 返回添加員工頁面
	}

	/*
	 * 處理添加員工的POST請求並驗證用戶輸入
	 */
	@PostMapping("insert")
	public String insert(@Valid CoachMember coachMember, BindingResult result, ModelMap model,
			@RequestParam("personalPhotos") MultipartFile[] parts) throws IOException {

		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(coachMember, result, "personalPhotos");

		if (parts[0].isEmpty()) { // 如果使用者未上傳圖片
			model.addAttribute("errorMessage", "會員照片: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] personalPhotos = multipartFile.getBytes();
				coachMember.setPersonalPhotos(personalPhotos); // 將圖片轉換為byte數組並設置到CoachMember
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) { // 如果有錯誤或未上傳圖片
			return "back-end/coach_member/addCoachMember"; // 返回添加頁面
		}

		// 新增員工資料
		coachMemberSvc.addCoachMember(coachMember);

		// 新增完成,準備轉交
		List<CoachMember> list = coachMemberSvc.getAll(); // 獲取所有員工資料
		model.addAttribute("coachMemberListData", list); // 添加員工列表到模型
		model.addAttribute("success", "- (新增成功)"); // 添加成功信息到模型
		return "redirect:/coach_member/listAllCoachMember"; // 重定向到員工列表頁面
	}

	/*
	 * 處理從列表頁面查詢特定員工的POST請求
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("cMemberID") String cMemberID, ModelMap model) {
		// 開始查詢資料
		CoachMember coachMember = coachMemberSvc.getOneCoachMember(Integer.valueOf(cMemberID)); // 根據cMemberID查詢員工

		// 查詢完成,準備轉交
		model.addAttribute("coachMember", coachMember); // 添加員工資料到模型
		return "back-end/coach_member/update_coachMember_input"; // 返回更新頁面
	}

	/*
	 * 處理更新員工資料的POST請求並驗證用戶輸入
	 */
	@PostMapping("update")
	public String update(@Valid CoachMember coachMember, BindingResult result, ModelMap model,
			@RequestParam("personalPhotos") MultipartFile[] parts) throws IOException {

		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(coachMember, result, "personalPhotos");

		if (parts[0].isEmpty()) { // 如果使用者未選擇新圖片
			byte[] personalPhotos = coachMemberSvc.getOneCoachMember(coachMember.getCMemberID()).getPersonalPhotos();
			coachMember.setPersonalPhotos(personalPhotos); // 保留原圖片
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] personalPhotos = multipartFile.getBytes();
				coachMember.setPersonalPhotos(personalPhotos); // 設置新圖片
			}
		}
		if (result.hasErrors()) { // 如果有錯誤
			return "back-end/coach_member/update_coachMember_input"; // 返回更新頁面
		}

		// 開始修改資料
		coachMemberSvc.updateCoachMember(coachMember);

		// 修改完成,準備轉交
		model.addAttribute("success", "- (修改成功)"); // 添加成功信息到模型
		coachMember = coachMemberSvc.getOneCoachMember(Integer.valueOf(coachMember.getCMemberID())); // 重新查詢更新後的員工資料
		model.addAttribute("coachMember", coachMember); // 添加員工資料到模型
		return "back-end/coach_member/listOneCoachMember"; // 返回單個員工資料頁面
	}

	/*
	 * 處理刪除員工的POST請求
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("cMemberID") String cMemberID, ModelMap model) {
		// 開始刪除資料
		coachMemberSvc.deleteCoachMember(Integer.valueOf(cMemberID)); // 根據cMemberID刪除員工

		// 刪除完成,準備轉交
		List<CoachMember> list = coachMemberSvc.getAll(); // 獲取所有員工資料
		model.addAttribute("coachMemberListData", list); // 添加員工列表到模型
		model.addAttribute("success", "- (刪除成功)"); // 添加成功信息到模型
		return "back-end/coach_member/listAllCoachMember"; // 返回員工列表頁面
	}

	/*
	 * 處理複合查詢的POST請求
	 */
	@PostMapping("listCoachMembers_ByCompositeQuery")
	public String listAllCoachMember(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap(); // 獲取請求參數
		List<CoachMember> list = coachMemberSvc.getAll(map); // 根據請求參數進行查詢
		model.addAttribute("coachMemberListData", list); // 添加查詢結果到模型
		return "back-end/coach_member/listAllCoachMember"; // 返回員工列表頁面
	}

	@GetMapping("/details")
	public String getCoachMemberDetails(@RequestParam("cMemberID") String cMemberID, Model model) {
		CoachMember coachMember = coachMemberSvc.getOneCoachMember(Integer.valueOf(cMemberID));
		model.addAttribute("coachMember", coachMember);
		return "back-end/coach_member/coachMemberDetails"; // 返回教練會員詳細資料頁面
	}

	/*
	 * 去除BindingResult中某個欄位的FieldError紀錄
	 */
	public BindingResult removeFieldError(CoachMember coachMember, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList()); // 過濾掉指定欄位的錯誤
		result = new BeanPropertyBindingResult(coachMember, "coachMember"); // 創建新的BindingResult
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError); // 添加過濾後的錯誤
		}
		return result; // 返回更新後的BindingResult
	}
}