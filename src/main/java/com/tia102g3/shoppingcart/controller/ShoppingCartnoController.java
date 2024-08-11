package com.tia102g3.shoppingcart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;
import com.tia102g3.shoppingcart.model.ShoppingCartService;
import com.tia102g3.shoppingcart.model.ShoppingCartVO;


@Controller
@Validated
@RequestMapping("/shoppingcart")
public class ShoppingCartnoController {
	
	@GetMapping("/test")
		public String test(ModelMap model) {
		
		return "frontend/product/shopping_cart";
	}
	
	@Autowired
	ShoppingCartService scSvc;
	
	@Autowired
	ProductService productSvc;
	

	/*
	 * This method will serve as addEmp.html handler.
	 */
//	@GetMapping("addEmp")
//	public String addEmp(ModelMap model) {
//		EmpVO empVO = new EmpVO();
//		model.addAttribute("empVO", empVO);
//		return "back-end/emp/addEmp";
//	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid ShoppingCartVO scVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(scVO, result, "upFiles");

		if (result.hasErrors()) {
			return "frontend/product/product_intro";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		scSvc.insertSCart(scVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<ShoppingCartVO> list = scSvc.getAll();
		model.addAttribute("scListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/frontend/product/shopping_cart"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}
	
	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid ShoppingCartVO scVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(scVO, result, "upFiles");

		
		if (result.hasErrors()) {
			return "frontend/product/product_intro";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		scSvc.updateSCart(scVO);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		scVO = scSvc.findByPK(Integer.valueOf(scVO.getShoppingCartID()));
		model.addAttribute("scVO", scVO);
		return "frontend/product/shopping_cart"; // 修改成功後轉交listOneEmp.html
	}
	
	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("shoppingCartID") String shoppingCartID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		ShoppingCartVO scVO = scSvc.findByPK(Integer.valueOf(shoppingCartID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("scVO", scVO);
		return "frontend/product/shopping_cart"; // 查詢完成後轉交update_emp_input.html
	}
	
	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("shoppingCartID") String shoppingCartID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		scSvc.deleteSCart(Integer.valueOf(shoppingCartID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<ShoppingCartVO> list = scSvc.getAll();
		model.addAttribute("scListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "frontend/product/shopping_cart"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="購物車編號: 請勿空白")
		@Digits(integer = 4, fraction = 0, message = "購物車編號: 請填數字-請勿超過{integer}位數")
		@Min(value = 0001, message = "員工編號: 不能小於{value}")
		@Max(value = 9999, message = "員工編號: 不能超過{value}")
		@RequestParam("shoppingCartID") String shoppingCartID,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
//		ShoppingCartService scSvc = new ShoppingCartService();
		ShoppingCartVO scVO = scSvc.findByPK(Integer.valueOf(shoppingCartID));
		
		List<ShoppingCartVO> list = scSvc.getAll();
		model.addAttribute("scListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("productVO", new ProductVO());  // for select_page.html 第133行用
		List<ProductVO> list2 = productSvc.getAll();
    	model.addAttribute("productListData",list2);    // for select_page.html 第135行用
		
		if (scVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "frontend/product/shopping_cart";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("scVO", scVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "back-end/emp/listOneEmp";  // 查詢完成後轉交listOneEmp.html
		return "frontend/product/shopping_cart"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
	}

	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   
//	    model.addAttribute("empVO", new EmpVO());
//    	EmpService empSvc = new EmpService();
		List<ShoppingCartVO> list = scSvc.getAll();
		model.addAttribute("scListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("productVO", new ProductVO());  // for select_page.html 第133行用
		List<ProductVO> list2 = productSvc.getAll();
    	model.addAttribute("productListData",list2);    // for select_page.html 第135行用
		String message = strBuilder.toString();
	    return new ModelAndView("frontend/product/shopping_cart", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(ShoppingCartVO scVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(scVO, "scVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
}