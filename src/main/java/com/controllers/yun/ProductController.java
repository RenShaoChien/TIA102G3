package com.controllers.yun;

import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    ProductService productservice;


    @RequestMapping(value = "/productList", method = {RequestMethod.GET, RequestMethod.POST})
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

            model.addAttribute("product", pd);

            return "backend/product/editProduct";
        }
        return "error";
    }

    @PostMapping("/addProduct.do")
    public String addSystemCourse(@Valid ProductVO pd, BindingResult result, @RequestParam("productImage") MultipartFile productImage, RedirectAttributes redirectAttributes) throws Exception {

        if (result.hasErrors()) {
            return "backend/product/addProduct";
        }
        try {
            productservice.updateProduct(pd, productImage);
            redirectAttributes.addFlashAttribute("message", "課程新增成功！"); // 將成功訊息添加到 model 中
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "操作失敗"); // 將失敗訊息添加到 model 中
        }
        return "redirect:/prouct/productList";
    }

    @PostMapping("/edit.do")
    public String editSystemCourseFinish(@Valid ProductVO pd, BindingResult result, @RequestParam("productImage") MultipartFile productImage, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "backend/prouct/editProduct";
        }

        productservice.updateProduct(pd, productImage);
        redirectAttributes.addFlashAttribute("message", "商品修改成功！"); // 將成功訊息添加到 model 中

        return "redirect:/prouct/productList";
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

        return "redirect:/product/productpage?pageNo=" + pageNo + (keyword != null ? "&keyword=" + keyword : "");
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException ex, Model model) {
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
        if (requestURI.contains("/addSystemCourse")) {
            return new ModelAndView("frames/add_system_course", model.asMap());
        } else {
            return new ModelAndView("error/default_error_page", model.asMap());
        }
    }

    public <T> BindingResult removeFieldError(T t, BindingResult result, String removedFieldname) {
        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
                .filter(fieldname -> !fieldname.getField().equals(removedFieldname))
                .collect(Collectors.toList());
        result = new BeanPropertyBindingResult(t, "\"" + t + "\"");
        for (FieldError fieldError : errorsListToKeep) {
            result.addError(fieldError);
        }
        return result;
    }


}
