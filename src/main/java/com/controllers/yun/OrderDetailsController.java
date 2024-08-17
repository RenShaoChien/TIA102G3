package com.controllers.yun;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.order.model.OrderVO;
import com.tia102g3.orderdetails.model.OrderDetailsService;
import com.tia102g3.orderdetails.model.OrderDetailsVO;
import com.tia102g3.product.model.ProductService;
import com.utils.StringUtil;

@Controller
@RequestMapping("/orderdetails")
@Validated
public class OrderDetailsController {

	@Autowired
	ProductService productservice;

	@Autowired
	OrderDetailsService orderDservice;

	@GetMapping("/orderDetailsList")
	public String orderDetailsList(Integer orderID, Model model) throws Exception {
//		HttpSession session = req.getSession();
//		if (pageNo == null) {
//			pageNo = 1;
//			session.setAttribute("pageNo", pageNo);
//		}
//		if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
//			pageNo = 1;
//			if (StringUtil.isEmpty(keyword)) {
//				keyword = "";
//			}
//			session.setAttribute("keyword", keyword);
//		} else {
//			Object keywordObj = session.getAttribute("keyword");
//			if (keywordObj != null) {
//				keyword = keywordObj.toString();
//			} else {
//				keyword = "";
//			}
//		}
//		session.setAttribute("pageNo", pageNo);
//		int offset = (pageNo - 1) * 5;
//		List<OrderDetailsVO> orderDetailsList = orderDservice.getAll();
//		session.setAttribute("orderDetailsList", orderDetailsList);
//
//		int totalRecords = orderDservice.getOrderDetailsCount(keyword).intValue();
//		int pageCount = (int) Math.ceil((double) totalRecords / 5); // 計算總頁數
//		session.setAttribute("pageCount", pageCount);

		List<OrderDetailsVO> orderDetailList = orderDservice.findAllById(orderID);
		System.out.println(orderDetailList);
		model.addAttribute("orderDetailsList", orderDetailList);
		return "/backend/orderdetails/orderDetailsPage";
	}

	@GetMapping("/addOrderDetails")
	public String addOrderDetails(ModelMap model) {
		OrderDetailsVO odv = new OrderDetailsVO();
		model.addAttribute("orderdetails", odv);
		return "backend/orderdetails/addOrderDetails";
	}

	@PostMapping("/addOrderDetails.do")
	public String addOrder(OrderDetailsVO orderDetailsVO, BindingResult result, RedirectAttributes redirectAttributes, Model model)
			throws Exception {

		if (result.hasErrors()) {
			return "backend/orderdetails/addOrderDetails";
		}
		try {
			orderDservice.updateOrderDetails(orderDetailsVO);
			redirectAttributes.addFlashAttribute("message", "訂單明細新增成功！"); // 將成功訊息添加到 model 中
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "操作失敗"); // 將失敗訊息添加到 model 中
		}
		return "redirect:/orderdetails/orderDetailsList";
	}

	/*
	 * @GetMapping("/edit") public String editProduct(ModelMap model, @RequestParam
	 * Integer productID) throws Exception { if (productID != null) {
	 * 
	 * ProductVO pd = productservice.findProductById(productID); // byte[] pics =
	 * productservice.getProductPic(productID);
	 * 
	 * model.addAttribute("product", pd); // model.addAttribute("pics", pics);
	 * 
	 * return "backend/product/editProduct"; } return "error"; }
	 */

	/*
	 * @PostMapping("/edit.do") public String editSystemCourseFinish(@Valid
	 * ProductVO pd, BindingResult result, @RequestParam("productImage")
	 * MultipartFile productImage, RedirectAttributes redirectAttributes) throws
	 * IOException { if (result.hasErrors()) { return "backend/product/editProduct";
	 * }
	 * 
	 * productservice.updateProduct(pd, productImage);
	 * redirectAttributes.addFlashAttribute("message", "商品修改成功！"); // 將成功訊息添加到 model
	 * 中
	 * 
	 * return "redirect:/product/productList"; }
	 */

	@GetMapping("/delProduct.do") 
	  public String delSystemCourse(ModelMap model, @RequestParam Integer ordDtIID, HttpSession session) 
	{ Object pageNoObj = session.getAttribute("pageNo"); int pageNo = 0; if (pageNoObj !=
	  null) { pageNo = Integer.parseInt(pageNoObj.toString()); }
	  
	  Object keywordObj = session.getAttribute("keyword"); String keyword = ""; if
	  (keywordObj != null) { keyword = keywordObj.toString(); }
	  orderDservice.deleteOrderDetails(ordDtIID);
	  
//	  orderDservice.getOrderDetailsCount(keyword).intValue();
//	  if (pageNo > totalRecords && totalRecords > 0) { 
//		  pageNo = totalRecords; } else if (totalRecords == 0) { 
//			  pageNo = 1;
//	  }

	  return "redirect:/orderdetails/orderDetailsList?pageNo="; 
//	  + pageNo + (keyword != null ?
//	  "&keyword=" + keyword : "");
	  }

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException ex,
			Model model) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation<?> violation : violations) {
			errorMessages.add(violation.getMessage());
		}
		OrderDetailsVO odv = (OrderDetailsVO) model.getAttribute("orderDetailsVO");
		if (odv == null) {
			odv = new OrderDetailsVO(); // 如果不存在，則創建一個新的
		}
		model.addAttribute("validationErrors", errorMessages);
		model.addAttribute("orderDetailsVO", odv);

		String requestURI = req.getRequestURI();
		if (requestURI.contains("/addOrderDetails")) {
			return new ModelAndView("backend/order/addOrderDetails", model.asMap());
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
