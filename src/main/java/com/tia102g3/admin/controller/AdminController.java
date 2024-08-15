package com.tia102g3.admin.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g3.admin.model.AdminService;
import com.tia102g3.admin.model.AdminVO;
import com.tia102g3.product.model.ProductVO;
import com.utils.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminSvc;
	

	@GetMapping("addAdmin")
	public String addAdmin(ModelMap model) {
		AdminVO adminVO = new AdminVO();
		model.addAttribute("adminVO", adminVO);
		return "back-end/admin/addAdmin";
	}

	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("adminVO") AdminVO adminVO, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "back-end/admin/addAdmin";
		}
		// ----------2.開始新增資料
		adminSvc.addAdmin(adminVO);
		// --------3.新增完成,準備轉交(Send the Success view)
		List<AdminVO> list = adminSvc.getAll();
		model.addAttribute("adminListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/admin/listAllAdmin";
	}

	@PostMapping("getOne_For_Update")
	public String getOneForUpdate(@RequestParam("admin_ID") String admin_ID, ModelMap model) {
		AdminVO adminVO = adminSvc.getOneAdmin(Integer.valueOf(admin_ID));
		model.addAttribute("adminVO", adminVO);
		return "back-end/admin/update_admin_input";
	}

	@PostMapping("update")
	public String update(@Valid AdminVO adminVO, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "back-end/admin/update_admin_input";
		}
		adminSvc.updateAdmin(adminVO);
		model.addAttribute("success", "- (修改成功)");
		adminVO = adminSvc.getOneAdmin(Integer.valueOf(adminVO.getAdmin_ID()));
		model.addAttribute("adminVO", adminVO);
		return "back-end/admin/listOneAdmin";
	}
	
	
	@PostMapping("delete")
	public String delete(@RequestParam("admin_ID") String admin_ID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		adminSvc.deleteAdmin(Integer.valueOf(admin_ID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<AdminVO> list = adminSvc.getAll();
		model.addAttribute("adminListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/admin/listAllAdmin"; // 刪除完成後轉交listAllAdmin.html
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(AdminVO adminVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(adminVO, "adminVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	@PostMapping("listAdmins_ByCompositeQuery")
	public String listAllAdmin(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<AdminVO> list = adminSvc.getAll(map);
		model.addAttribute("adminListData", list);
		return "back-end/admin/listAllAdmin";
	}
}
