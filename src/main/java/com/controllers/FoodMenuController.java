package com.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.food.model.FoodService;
import com.tia102g3.food.model.FoodVO;
import com.tia102g3.healthstatus.model.HealthStatusService;
import com.tia102g3.healthstatus.model.HealthStatusVO;
import com.tia102g3.likefood.model.LikeFoodService;
import com.tia102g3.likefood.model.LikeFoodVO;
import com.tia102g3.menu.model.MenuService;
import com.tia102g3.menu.model.MenuVO;

@Controller
@RequestMapping("/menu")
public class FoodMenuController {

    @Autowired
    FoodService foodSvc;
    
//    @Autowired
//    FoodListService foodListSvc;
    
    @Autowired
    HealthStatusService healthStatusSvc;
    
    @Autowired
    LikeFoodService likeFoodSvc;
    
    @Autowired
    MenuService menuSvc;

    @GetMapping("/backstage")
    public String backstage(ModelMap model) {

        return "menu/backstage";
    }

    @GetMapping("/addFood")
    public String addFood(ModelMap model) {
        FoodVO foodVO = new FoodVO();
        model.addAttribute("foodVO", foodVO);
        return "menu/addFood";
    }
    
    @GetMapping("/addHealthStatus")
    public String addHealthStatus(ModelMap model) {
        HealthStatusVO healthStatusVO = new HealthStatusVO();
        model.addAttribute("healthStatusVO", healthStatusVO);
        return "menu/addHealthStatus";
    }
    
    @GetMapping("/addLikeFood")
    public String addLikeFood(ModelMap model) {
        LikeFoodVO likeFoodVO = new LikeFoodVO();
        model.addAttribute("likeFoodVO", likeFoodVO);
        return "menu/addLikeFood";
    }
    
//    @GetMapping("/addFoodList")
//    public String addFoodList(ModelMap model) {
//        FoodListVO foodListVO = new FoodListVO();
//        model.addAttribute("foodListVO", foodListVO);
//        return "menu/addFoodList";
//    }
    
    

    @GetMapping("/listAllFood")
    public String listAllFood(ModelMap model) {
        List<FoodVO> list = foodSvc.getAll();
        model.addAttribute("foodListData", list);
        return "menu/listAllFood";
    }
    
//    @GetMapping("/listAllFoodList")
//    public String listAllMenu(ModelMap model) {
////        List<FoodListVO> list = foodListSvc.getOneByMenuNum(1);
//        List<FoodListVO> list = foodListSvc.getAll();
//        List<Integer> allMenuNum = foodListSvc.getAllMenuNum();
//
//        model.addAttribute("menuListData", list);
//        model.addAttribute("allMenuNum", allMenuNum);
////        model.addAttribute("allMenu", allMenu);
//        System.out.println(allMenuNum);
//        return "menu/listAllFoodList";
//    }
    
    @GetMapping("/listAllLikeFood")
    public String listAllLikeFood(ModelMap model) {
        List<LikeFoodVO> list = likeFoodSvc.getAll();
        model.addAttribute("likeFoodListData", list);
        return "menu/listAllLikeFood";
    }
    
    @GetMapping("/listAllMenu")
    public String listAllMenu(ModelMap model) {
        List<MenuVO> list = menuSvc.getAll();
        model.addAttribute("menuListData", list);
        return "menu/listAllMenu";
    }

    // =======================================
    @PostMapping("insert")
    public String insert(@Valid FoodVO foodVO, BindingResult result, ModelMap model) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
        
        if (result.hasErrors()) {
            return "menu/addFood";
        }

        /*************************** 2.開始新增資料 *****************************************/
        // EmpService empSvc = new EmpService();
        foodSvc.addFood(foodVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<FoodVO> list = foodSvc.getAll();
        model.addAttribute("foodListData", list);
        model.addAttribute("success", "- (新增成功)");

//        return "redirect:/menu/listAllFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
        return "menu/listAllFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
    }

    @PostMapping("update")
    public String update(@Valid FoodVO foodVO, BindingResult result, ModelMap model) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
//        result = removeFieldError(foodVO, result, "upFiles");

//        if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
//            // EmpService empSvc = new EmpService();
//            byte[] upFiles = empSvc.getOneEmp(empVO.getEmpno()).getUpFiles();
//            empVO.setUpFiles(upFiles);
//        } else {
//            for (MultipartFile multipartFile : parts) {
//                byte[] upFiles = multipartFile.getBytes();
//                empVO.setUpFiles(upFiles);
//            }
//        }
        if (result.hasErrors()) {
            return "menu/update_food_input";
        }
        /*************************** 2.開始修改資料 *****************************************/
        // EmpService empSvc = new EmpService();
        foodSvc.updateFood(foodVO);

        /*************************** 3.修改完成,準備轉交(Send the Success view) **************/
        model.addAttribute("success", "- (修改成功)");
        foodVO = foodSvc.getOneFood(Integer.valueOf(foodVO.getFoodNumber()));
        model.addAttribute("foodVO", foodVO);
        return "menu/listOneFood"; // 修改成功後轉交listOneEmp.html
    }
    
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("foodNumber") String foodNumber, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始查詢資料 *****************************************/
        // EmpService empSvc = new EmpService();
        FoodVO foodVO = foodSvc.getOneFood(Integer.valueOf(foodNumber));

        /*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
        model.addAttribute("foodVO", foodVO);
        return "menu/update_food_input"; // 查詢完成後轉交update_emp_input.html
    }
    
    @PostMapping("delete")
    public String delete(@RequestParam("foodNumber") String foodNumber, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始刪除資料 *****************************************/
        // EmpService empSvc = new EmpService();
//        empSvc.deleteEmp(Integer.valueOf(empno));
        /*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
        List<FoodVO> list = foodSvc.getAll();
        model.addAttribute("foodListData", list);
        model.addAttribute("success", "- (刪除成功)");
        return "menu/listAllFood"; // 刪除完成後轉交listAllEmp.html
    }
    
    
    // ======== add Health Status data ========
    @PostMapping("insertHealthStatus")
    public String insert(@Valid HealthStatusVO healthStatusVO, BindingResult result, ModelMap model) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
        
        if (result.hasErrors()) {
            return "menu/addHealthStatus";
        }

        /*************************** 2.開始新增資料 *****************************************/
        // EmpService empSvc = new EmpService();
        healthStatusSvc.addHealthStatus(healthStatusVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<HealthStatusVO> list = healthStatusSvc.getAll();
        model.addAttribute("healthStatusListData", list);
        model.addAttribute("success", "- (新增成功)");

//        return "redirect:/food/listAllFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
        return "menu/backstage"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
    }
    
    
    @PostMapping("insertLikeFood")
    public String insert(LikeFoodVO likeFoodVO, BindingResult result, ModelMap model, 
            @RequestParam("foodNumber") String foodNumber) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
        
        if (result.hasErrors()) {
            return "menu/addLikeFood";
        }

        /*************************** 2.開始新增資料 *****************************************/
        FoodVO foodVO = foodSvc.getOneFood(Integer.valueOf(foodNumber));
        likeFoodVO.setFoodVO(foodVO);
//        System.out.print("foodNumber: ");
//        System.out.println(foodNumber);
//        System.out.print("likeFoodVO: ");
//        System.out.println(likeFoodVO);
        likeFoodSvc.addLikeFood(likeFoodVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<LikeFoodVO> list = likeFoodSvc.getAll();
        model.addAttribute("likeFoodListData", list);
        model.addAttribute("success", "- (新增成功)");

//        return "redirect:/food/listAllFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
        return "menu/listAllLikeFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
    }
    
    
    @ModelAttribute("FoodListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<FoodVO> referenceListData(Model model) {
        
        List<FoodVO> list = foodSvc.getAll();
        return list;
    }

}
