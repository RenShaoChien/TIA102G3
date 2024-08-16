package com.controllers.mark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.food.model.FoodService;
import com.tia102g3.food.model.FoodVO;
import com.tia102g3.healthstatus.model.HealthStatusService;
import com.tia102g3.healthstatus.model.HealthStatusVO;
import com.tia102g3.likefood.model.LikeFoodService;
import com.tia102g3.likefood.model.LikeFoodVO;

@Controller
public class IndexMenuController {
    
    @Autowired
    FoodService foodSvc;
    
    @Autowired
    HealthStatusService healthStatusSvc;
    
    @Autowired
    LikeFoodService likeFoodSvc;

    @PostMapping("insertLikeFood")
    public String insert(@Valid LikeFoodVO likeFoodVO, BindingResult result, ModelMap model, 
            @RequestParam("foodNumber") String foodNumber) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        // 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
        
        if (result.hasErrors()) {
            return "menu";
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
//        List<LikeFoodVO> list = likeFoodSvc.getAll();
//        List<LikeFoodVO> list = likeFoodSvc.getOneLikeFood(likeFoodVO.getMemberID());
        List<LikeFoodVO> list = likeFoodSvc.getByMemberID(likeFoodVO.getMemberID());
//        System.out.println(likeFoodSvc.getByMemberID(likeFoodVO.getMemberID()));
//        System.out.println("MemberID: " + likeFoodVO.getMemberID());
        
        for(LikeFoodVO food : list) {
            System.out.print(food.getFoodVO().getFoodName()+", ");
            System.out.println(food.getFoodPreference());
        }
        
        List<FoodVO> foodList = foodSvc.getAll();
        model.addAttribute("FoodListData",foodList);
        model.addAttribute("likeFoodListData", list);
        model.addAttribute("success", "- (新增成功)");

        return "redirect:/menu"; 
//        return "redirect:/menu/listAllLikeFood"; 
    }
    
 // ======== add Health Status data ========
    @PostMapping("insertHealthStatus")
    public String insert(@Valid HealthStatusVO healthStatusVO, BindingResult result, ModelMap model) {

        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        if (result.hasErrors()) {
            return "menu";
        }
        /*************************** 2.開始新增資料 *****************************************/
        healthStatusSvc.addHealthStatus(healthStatusVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<HealthStatusVO> list = healthStatusSvc.getAll();
        model.addAttribute("healthStatusListData", list);
        model.addAttribute("success", "- (新增成功)");

        return "redirect:/menu";
    }
    
}
