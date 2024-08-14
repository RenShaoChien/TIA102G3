package com.controllers.yun;

import java.io.IOException;
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;
import com.utils.StringUtil;



@Controller
@RequestMapping("/product")
@Validated
public class ProductController {

	@Autowired
	ProductService productservice;
	
	@Autowired
	ProductRepository repository;

	@RequestMapping(value = "/productList", method = { RequestMethod.GET, RequestMethod.POST })
	public String productList(String oper, String keyword, Integer pageNo, HttpServletRequest req) throws Exception {
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
		List<ProductVO> productList = productservice.getProductList(keyword, offset);
		session.setAttribute("productList", productList);

		int totalRecords = productservice.getProductCount(keyword).intValue();
		int pageCount = (int) Math.ceil((double) totalRecords / 5); // 計算總頁數
		session.setAttribute("pageCount", pageCount);

		return "/backend/product/productpage";
	}

	@GetMapping("/addProduct")
	public String addProduct(ModelMap model) {
		ProductVO pd = new ProductVO();
		model.addAttribute("product", pd);
		return "backend/product/addProduct";
	}

	@GetMapping("/edit")
	public String editProduct(ModelMap model, @RequestParam Integer productID) throws Exception {
		if (productID != null) {

			ProductVO pd = productservice.findProductById(productID);
//            byte[] pics = productservice.getProductPic(productID);

			model.addAttribute("product", pd);
//            model.addAttribute("pics", pics);

			return "backend/product/editProduct";
		}
		return "error";
	}

	@PostMapping("/addProduct.do")
	public String addSystemCourse(@Valid ProductVO pd, BindingResult result,
			@RequestParam("productImage") MultipartFile productImage, RedirectAttributes redirectAttributes)
			throws Exception {

		if (result.hasErrors()) {
			return "backend/product/addProduct";
		}
		try {
			productservice.updateProduct(pd, productImage);
			redirectAttributes.addFlashAttribute("message", "商品新增成功！"); // 將成功訊息添加到 model 中
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "操作失敗"); // 將失敗訊息添加到 model 中
		}
		return "redirect:/product/productList";
	}

	@PostMapping("/edit.do")
	public String editSystemCourseFinish(@Valid ProductVO pd, BindingResult result,
			@RequestParam("productImage") MultipartFile productImage, RedirectAttributes redirectAttributes)
			throws IOException {
		if (result.hasErrors()) {
			return "backend/product/editProduct";
		}

		productservice.updateProduct(pd, productImage);
		redirectAttributes.addFlashAttribute("message", "商品修改成功！"); // 將成功訊息添加到 model 中

		return "redirect:/product/productList";
	}

	@GetMapping("/delProduct.do")
	public String delSystemCourse(ModelMap model, @RequestParam Integer productID, HttpSession session) {
		Object pageNoObj = session.getAttribute("pageNo");
		int pageNo = 0;
		if (pageNoObj != null) {
			pageNo = Integer.parseInt(pageNoObj.toString());
		}

		Object keywordObj = session.getAttribute("keyword");
		String keyword = "";
		if (keywordObj != null) {
			keyword = keywordObj.toString();
		}
		// 刪除系統課程
		productservice.deleteProduct(productID);

		// 檢查當前頁是否還有內容
		int totalRecords = productservice.getProductCount(keyword).intValue();
		// 確保當前頁碼在有效範圍內
		if (pageNo > totalRecords && totalRecords > 0) {
			pageNo = totalRecords;
		} else if (totalRecords == 0) {
			pageNo = 1; // 如果刪除後沒有任何課程，設置頁碼為1
		}

		return "redirect:/product/productList?pageNo=" + pageNo + (keyword != null ? "&keyword=" + keyword : "");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException ex,
			Model model) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation<?> violation : violations) {
			errorMessages.add(violation.getMessage());
		}
		ProductVO pd = (ProductVO) model.getAttribute("productVO");
		if (pd == null) {
			pd = new ProductVO(); // 如果不存在，則創建一個新的
		}
		model.addAttribute("validationErrors", errorMessages);
		model.addAttribute("productVO", pd);

		String requestURI = req.getRequestURI();
		if (requestURI.contains("/addProduct")) {
			return new ModelAndView("backend/product/addProduct", model.asMap());
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

	// ============前台頁面=======================//

	@GetMapping("/products")
    public String getProducts(Model model) {
        System.out.println("ProductController: 開始調用 productService.getAll()");
        List<ProductVO> products = productservice.getAll();
        model.addAttribute("productList", products);
        System.out.println("ProductController: 添加了 " + products.size() + " 個產品到模型中");
        System.out.println("ProductController: 返回 'pricing' 視圖");
        return "pricing";
    }
	
	
//	@PostMapping("getOne_For_Display")
//	public String getOne_For_Display(
//		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
////		@NotEmpty(message="員工編號: 請勿空白")
////		@Digits(integer = 4, fraction = 0, message = "員工編號: 請填數字-請勿超過{integer}位數")
////		@Min(value = 7001, message = "員工編號: 不能小於{value}")
////		@Max(value = 7777, message = "員工編號: 不能超過{value}")
//		@RequestParam("productID") String productID,
//		ModelMap model) {
//		
//		/***************************2.開始查詢資料*********************************************/
////		EmpService empSvc = new EmpService();
//		ProductVO pv = productservice.findProductById(Integer.valueOf(productID));
//		
//		List<ProductVO> list = productservice.getAll();
//		model.addAttribute("productListData", list);     // for select_page.html 第97 109行用
////		model.addAttribute("deptVO", new DeptVO());  // for select_page.html 第133行用
////		List<DeptVO> list2 = deptSvc.getAll();
////    	model.addAttribute("deptListData",list2);    // for select_page.html 第135行用
//		
//		if (pv == null) {
//			model.addAttribute("errorMessage", "查無資料");
//			return "pricing";
//		}
//		
//		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
//		model.addAttribute("pv", pv);
//		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
//		
////		return "back-end/emp/listOneEmp";  // 查詢完成後轉交listOneEmp.html
//		return "pricing"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
//	}
	
//	@GetMapping("/getOne_For_Display/{productID}")
//	@ResponseBody
//	public ResponseEntity<ProductVO> getOneForDisplay(@PathVariable("productId") Integer productID) {
//	    ProductVO product = productservice.findProductById(productID);
//        if (product != null) {
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//	}
	
//    @ModelAttribute("data-product-id")
//    protected List<ProductVO> productList(Model model){
//    	List<ProductVO> products = productservice.findProductById(productID);
//    	return repository.getReferenceById(productID);
//    }
	
	@RequestMapping("/product/getOne_For_Display")
	@PostMapping("/getOne_For_Display")
	@ResponseBody
	public ProductVO getOneForDisplay(@RequestParam("productID") String productID) {
	    // 從資料庫查找對應的產品
	    ProductVO product = productservice.findProductById(Integer.valueOf(productID));
	    return product;  // 返回 JSON
	}

	
	



}
