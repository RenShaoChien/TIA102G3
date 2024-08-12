package com.tia102g3.courseorder.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.service.CourseOrderService;

@Controller
@RequestMapping("/course_order")
public class CourseOrderController {

    @Autowired
    CourseOrderService courseOrderSvc; // 注入CourseOrderService

    /*
     * 顯示添加課程訂單的頁面
     */
    @GetMapping("addCourseOrder")
    public String addCourseOrder(ModelMap model) {
        CourseOrder courseOrder = new CourseOrder();
        model.addAttribute("courseOrder", courseOrder); // 綁定空的CourseOrder到模型
        return "back-end/course_order/addCourseOrder"; // 返回添加課程訂單頁面
    }

    /*
     * 處理添加課程訂單的POST請求並驗證用戶輸入
     */
    @PostMapping("insert")
    public String insert(@Valid CourseOrder courseOrder, BindingResult result, ModelMap model) throws IOException {
        // 去除BindingResult中不需要的FieldError紀錄
//        result = removeFieldError(courseOrder, result, "personalPhotos");

        // 新增課程訂單資料
        courseOrderSvc.addCourseOrder(courseOrder);

        // 新增完成,準備轉交
        List<CourseOrder> list = courseOrderSvc.getAll(); // 獲取所有課程訂單資料
        model.addAttribute("courseOrderListData", list); // 添加課程訂單列表到模型
        model.addAttribute("success", "- (新增成功)"); // 添加成功信息到模型
        return "redirect:/course_order/listAllCourseOrder"; // 重定向到課程訂單列表頁面
    }

    /*
     * 處理從列表頁面查詢特定課程訂單的POST請求
     */
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("courseOrderID") String courseOrderID, ModelMap model) {
        // 開始查詢資料
        CourseOrder courseOrder = courseOrderSvc.getOneCourseOrder(Integer.valueOf(courseOrderID)); // 根據courseOrderID查詢課程訂單

        // 查詢完成,準備轉交
        model.addAttribute("courseOrder", courseOrder); // 添加課程訂單資料到模型
        return "back-end/course_order/update_courseOrder_input"; // 返回更新頁面
    }

    /*
     * 處理更新課程訂單資料的POST請求並驗證用戶輸入
     */
    @PostMapping("update")
    public String update(@Valid CourseOrder courseOrder, BindingResult result, ModelMap model) throws IOException {
        // 去除BindingResult中不需要的FieldError紀錄
//        result = removeFieldError(courseOrder, result, "personalPhotos");

        // 開始修改資料
        courseOrderSvc.updateCourseOrder(courseOrder);

        // 修改完成,準備轉交
        model.addAttribute("success", "- (修改成功)"); // 添加成功信息到模型
        courseOrder = courseOrderSvc.getOneCourseOrder(Integer.valueOf(courseOrder.getCourseOrderID())); // 重新查詢更新後的課程訂單資料
        model.addAttribute("courseOrder", courseOrder); // 添加課程訂單資料到模型
        return "back-end/course_order/listOneCourseOrder"; // 返回單個課程訂單資料頁面
    }

    /*
     * 處理刪除課程訂單的POST請求
     */
    @PostMapping("delete")
    public String delete(@RequestParam("courseOrderID") String courseOrderID, ModelMap model) {
        // 開始刪除資料
        courseOrderSvc.deleteCourseOrder(Integer.valueOf(courseOrderID)); // 根據courseOrderID刪除課程訂單

        // 刪除完成,準備轉交
        List<CourseOrder> list = courseOrderSvc.getAll(); // 獲取所有課程訂單資料
        model.addAttribute("courseOrderListData", list); // 添加課程訂單列表到模型
        model.addAttribute("success", "- (刪除成功)"); // 添加成功信息到模型
        return "back-end/course_order/listAllCourseOrder"; // 返回課程訂單列表頁面
    }

    /*
     * 去除BindingResult中某個欄位的FieldError紀錄
     */
    public BindingResult removeFieldError(CourseOrder courseOrder, BindingResult result, String removedFieldname) {
        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
                .filter(fieldname -> !fieldname.getField().equals(removedFieldname))
                .collect(Collectors.toList()); // 過濾掉指定欄位的錯誤
        result = new BeanPropertyBindingResult(courseOrder, "courseOrder"); // 創建新的BindingResult
        for (FieldError fieldError : errorsListToKeep) {
            result.addError(fieldError); // 添加過濾後的錯誤
        }
        return result; // 返回更新後的BindingResult
    }

    /*
     * 處理複合查詢的POST請求
     */
    @PostMapping("listCourseOrders_ByCompositeQuery")
    public String listAllCourseOrder(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap(); // 獲取請求參數
        List<CourseOrder> list = courseOrderSvc.getAll(map); // 根據請求參數進行查詢
        model.addAttribute("courseOrderListData", list); // 添加查詢結果到模型
        return "back-end/course_order/listAllCourseOrder"; // 返回課程訂單列表頁面
    }
}
