package com.tia102g3.foodlist.controller;

import java.util.List;

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
import com.tia102g3.foodlist.model.FoodListService;
import com.tia102g3.foodlist.model.FoodListVO;
import com.tia102g3.menu.model.MenuService;
import com.tia102g3.menu.model.MenuVO;

@Controller
@RequestMapping("/menu")
public class FoodListController {

    @Autowired
    FoodListService foodListSvc;
    
    @Autowired
    FoodService foodSvc;
    
    @Autowired
    MenuService menuSvc;
    
    @GetMapping("/addFoodList")
    public String addFoodList(ModelMap model) {
        FoodListVO foodListVO = new FoodListVO();
        model.addAttribute("foodListVO", foodListVO);
        return "menu/addFoodList";
    }
    
    
    @GetMapping("/listAllFoodList")
    public String listAllMenu(ModelMap model) {
//        List<FoodListVO> list = foodListSvc.getOneByMenuNum(1);
        List<FoodListVO> list = foodListSvc.getAll();
//        List<Integer> allMenuNum = foodListSvc.getAllMenuNum();

        model.addAttribute("foodListData", list);
//        model.addAttribute("allMenuNum", allMenuNum);
//        model.addAttribute("allMenu", allMenu);
//        System.out.println(allMenuNum);
        return "menu/listAllFoodList";
    }
    
    // ======== add Food List data ========
    @PostMapping("insertFoodList")
    public String insert(FoodListVO foodListVO, BindingResult result, ModelMap model,
            @RequestParam("menuNumber") String menuNumber,
            @RequestParam("foodNumber") String foodNumber ) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
        
        if (result.hasErrors()) {
            return "menu/addFoodList";
        }

        /*************************** 2.開始新增資料 *****************************************/
        FoodVO foodVO = foodSvc.getOneFood(Integer.valueOf(foodNumber));
        MenuVO menuVO = menuSvc.getOneMenu(Integer.valueOf(menuNumber));
        foodListVO.setFoodVO(foodVO);
        foodListVO.setMenuVO(menuVO);
        
        foodListSvc.addFoodList(foodListVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<FoodListVO> list = foodListSvc.getAll();
        model.addAttribute("foodListData", list);
        model.addAttribute("success", "- (新增成功)");

//        return "redirect:/food/listAllFood"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
        return "menu/listAllFoodList"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
    }
    
    @ModelAttribute("FoodListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<FoodVO> referenceListData(Model model) {
        
        List<FoodVO> list = foodSvc.getAll();
        return list;
    }
    
    
}
