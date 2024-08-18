package com.controllers;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.service.MemberService;
import com.tia102g3.product.model.ProductService;

/**
 * ClassName： PageController package：com.controllers Description：
 *
 * @Author 任少騫
 * @Create 2024/8/2 上午12:17
 * @Version 1.0
 */
@Controller
public class PageController {
	
	@Autowired
	ProductService productservice;


    @Autowired
    private CoachCourseServiceImpl ccService;
    
    @Autowired
    private MemberService memberSvc;
    
    @Autowired
    private CoachMemberService coachMemberSvc;

//    @Autowired
//    private FoodService foodSvc;
//
//    @Autowired
//    private MenuService menuSvc;


    @GetMapping({"/", "/index"})
    public String indexPage(HttpServletRequest request, HttpSession session, Model model) {
        // 檢查是否登入
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            // 如果未登入，將 "isLoggedIn" 設為 false
            model.addAttribute("isLoggedIn", false);

            // 確保未登入的用戶只能訪問特定頁面
            String uri = request.getRequestURI();
            if (!uri.equals("/login") && !uri.equals("/index") && !uri.startsWith("/map") && !uri.equals("/qa")) {
                return "redirect:/login?error=not_logged_in";
            }
        } else {
            model.addAttribute("isLoggedIn", true);

            // 確保用戶在訪問 index 頁面時，session 中有 user 資料
            Object user = session.getAttribute("user");
            if (user instanceof Member) { // 如果是一般會員
                Member member = (Member) user;
                Integer memberID = member.getMemberID();
                Member memberDetails = memberSvc.findById(memberID);

                if (memberDetails != null) {
                    model.addAttribute("member", memberDetails); // 添加 Member 對象到模型中
                    model.addAttribute("name", memberDetails.getName()); // 添加 name 屬性到模型中
                }
            } else if (user instanceof CoachMember) { // 如果是教練會員
                CoachMember coachMember = (CoachMember) user;
                Integer coachMemberID = coachMember.getCMemberID();
                CoachMember coachMemberDetails = coachMemberSvc.findById(coachMemberID);

                if (coachMemberDetails != null) {
                    model.addAttribute("coachMember", coachMemberDetails); // 添加 CoachMember 對象到模型中
                    model.addAttribute("name", coachMemberDetails.getName()); // 添加 name 屬性到模型中
                }
            }
        }
        return "index"; // 返回 index 頁面的視圖名稱
    }


    @GetMapping("/trainers")
    public String trainers(Model model) {
        model.addAttribute("coachCourseList", ccService.findAllByStatus(CourseStatus.IN_PROGRESS));
        return "trainers";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }


//    @GetMapping("/menu")
//    public String menu(Model model) {
//        LikeFoodVO likeFoodVO = new LikeFoodVO();
//        List<MenuVO> menuVO = new ArrayList<>();
//        menuVO = menuSvc.getAll();
//        model.addAttribute("likeFoodVO", likeFoodVO);
//        model.addAttribute("FoodListData", foodSvc.getAll());
//        model.addAttribute("menuVO", menuVO);
//        return "menu";
//    }

//    @GetMapping("/menu")
//    public String menu(Model model) {
//        return "menu";
//    }


    @GetMapping("/member")
    public String member(Model model) {
        return "member";
    }


//    @GetMapping("/pricing")
//    public String pricing(Model model) {
//        return "pricing";
//    }
    
    @GetMapping("/productintro")
    public String productintro(Model model) {
        return "product/product_intro";
    }
    
//    @GetMapping("/shoppingcart")
//    public String shoppingcart(Model model) {
//        return "product/shopping_cart";
//    }
    
//    @ModelAttribute("productList")
//    protected List<ProductVO> productList(Model model){
//    	List<ProductVO> products = productservice.getAll();
//    	return products;
//    }
    

    @GetMapping("/orderChecking")
    public String OrderChecking(Model model) {
        return "product/order_checking";
    }
    
    @GetMapping("/qa")
    public String qaPage() {
        return "qa"; 
    }

    
    @GetMapping("/contact")
    public String contactPage() {
        return "contact"; 
    }
    
    @GetMapping("/foodmap")
    public String foodmapPage() {
        return "foodmap"; 
    }
    
}
