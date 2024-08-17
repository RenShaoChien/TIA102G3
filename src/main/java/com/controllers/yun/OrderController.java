package com.controllers.yun;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g3.order.model.OrderService;
import com.tia102g3.order.model.OrderVO;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;
import com.utils.StringUtil;

@Controller
@RequestMapping("/order")
@Validated
public class OrderController {

	@Autowired
	ProductService productservice;

	@Autowired
	OrderService orderservice;

	// 為了處理前端發送的空字串並避免時間格式錯誤，你可以在後端將空字串轉換為null，或者在轉換過程中忽略這些空值。這可以通過自訂的轉換器或更新你的字段設置來完成。
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);

		// 自訂 Timestamp 編輯器
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/orderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderList(String oper, String keyword, Integer pageNo, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		if (pageNo == null) {
			pageNo = 1;
			session.setAttribute("pageNo", pageNo);
		}
		if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
			pageNo = 1;
			if (StringUtil.isEmpty(keyword)) {
				keyword = "";
			}
			session.setAttribute("keyword", keyword);
		} else {
			Object keywordObj = session.getAttribute("keyword");
			if (keywordObj != null) {
				keyword = keywordObj.toString();
			} else {
				keyword = "";
			}
		}
		session.setAttribute("pageNo", pageNo);
		int offset = (pageNo - 1) * 5;
		List<OrderVO> orderList = orderservice.getOrdertList(keyword, offset);
		session.setAttribute("orderList", orderList);

		int totalRecords = orderservice.getOrderCount(keyword).intValue();
		int pageCount = (int) Math.ceil((double) totalRecords / 5); // 計算總頁數
		session.setAttribute("pageCount", pageCount);

		return "/backend/order/orderpage";
	}

	@GetMapping("/addOrder")
	public String addOrder(ModelMap model) {
		OrderVO ov = new OrderVO();
		model.addAttribute("order", ov);
		return "backend/order/addOrder";
	}

	
	 @GetMapping("/edit") 
	 public String editOrder(ModelMap model, @RequestParam Integer orderID) throws Exception { if (orderID != null) {
	 OrderVO ov = orderservice.findOrderById(orderID); 
	 model.addAttribute("order", ov);
	 return "backend/order/editOrder"; } return "error"; } 
	 

	@PostMapping("/addOrder.do")
	public String addOrder(OrderVO ov, BindingResult result, RedirectAttributes redirectAttributes, Model model)
			throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("order", ov);
			return "/backend/order/addOrder";
		}
		try {
			orderservice.updateOrder(ov);
			redirectAttributes.addFlashAttribute("message", "訂單新增成功！"); // 將成功訊息添加到 model 中
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "操作失敗"); // 將失敗訊息添加到 model 中
		}
		return "redirect:/order/orderList";
	}

	
	@PostMapping("/edit.do")
	public String editSystemCourseFinish(@Valid OrderVO ov, BindingResult result, RedirectAttributes redirectAttributes)
		throws IOException {
		if (result.hasErrors()) {
			return "backend/order/editOrder";
		}

		orderservice.updateOrder(ov);
		redirectAttributes.addFlashAttribute("message", "訂單狀態修改成功！"); // 將成功訊息添加到 model 中

		return "redirect:/order/orderList";
	}
	
//	@PostMapping("/order/edit.do")
//	public String updateOrderStatus(@RequestParam("orderID") int orderID, 
//	                                @RequestParam("status") String status) {
//	    OrderVO order = orderservice.findById(orderID);
//	    if (order != null) {
//	        order.setStatus(status);
//	        orderservice.save(order); // 僅更新訂單狀態
//	    }
//	    return "redirect:/order/orderList"; // 重定向至訂單頁面
//	}

	  
	 /* 
	 * @GetMapping("/delProduct.do") public String delSystemCourse(ModelMap
	 * model, @RequestParam Integer productID, HttpSession session) { Object
	 * pageNoObj = session.getAttribute("pageNo"); int pageNo = 0; if (pageNoObj !=
	 * null) { pageNo = Integer.parseInt(pageNoObj.toString()); }
	 * 
	 * Object keywordObj = session.getAttribute("keyword"); String keyword = ""; if
	 * (keywordObj != null) { keyword = keywordObj.toString(); } // 刪除系統課程
	 * productservice.deleteProduct(productID);
	 * 
	 * // 檢查當前頁是否還有內容 int totalRecords =
	 * productservice.getProductCount(keyword).intValue(); // 確保當前頁碼在有效範圍內 if
	 * (pageNo > totalRecords && totalRecords > 0) { pageNo = totalRecords; } else
	 * if (totalRecords == 0) { pageNo = 1; // 如果刪除後沒有任何課程，設置頁碼為1 }
	 * 
	 * return "redirect:/product/productList?pageNo=" + pageNo + (keyword != null ?
	 * "&keyword=" + keyword : ""); }
	 */

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException ex,
			Model model) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation<?> violation : violations) {
			errorMessages.add(violation.getMessage());
		}
		OrderVO ov = (OrderVO) model.getAttribute("orderVO");
		if (ov == null) {
			ov = new OrderVO(); // 如果不存在，則創建一個新的
		}
		model.addAttribute("validationErrors", errorMessages);
		model.addAttribute("orderVO", ov);

		String requestURI = req.getRequestURI();
		if (requestURI.contains("/addOrder")) {
			return new ModelAndView("backend/order/addOrder", model.asMap());
		} else {
			return new ModelAndView("error/default_error_page", model.asMap());
		}
	}

	public <T> BindingResult removeFieldError(T t, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(t, "\"" + t + "\"");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

}
