package com.controllers;

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
import org.springframework.web.multipart.MultipartFile;

import com.tia102g3.member.model.Member;
import com.tia102g3.member.service.MemberService;




@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberSvc; // 注入 MemberService

    /*
     * 顯示添加會員的頁面
     */
    @GetMapping("addMember")
    public String addMember(ModelMap model) {
        Member member = new Member();
        model.addAttribute("member", member); // 綁定空的 Member 到模型
        return "back-end/member/addMember"; // 返回添加會員頁面
    }

    /*
     * 處理添加會員的 POST 請求並驗證用戶輸入
     */
    @PostMapping("insert")
    public String insert(@Valid Member member, BindingResult result, ModelMap model,
                         @RequestParam("personalPhotos") MultipartFile[] parts) throws IOException {

        // 去除 BindingResult 中 personalPhotos 欄位的 FieldError 紀錄
        result = removeFieldError(member, result, "personalPhotos");

        if (parts[0].isEmpty()) { // 如果使用者未上傳圖片
            model.addAttribute("errorMessage", "會員照片: 請上傳照片");
        } else {
            for (MultipartFile multipartFile : parts) {
                byte[] personalPhotos = multipartFile.getBytes();
                member.setPersonalPhotos(personalPhotos); // 將圖片轉換為 byte 數組並設置到 Member
            }
        }
        if (result.hasErrors() || parts[0].isEmpty()) { // 如果有錯誤或未上傳圖片
            return "back-end/member/addMember"; // 返回添加頁面
        }

        // 新增會員資料
        memberSvc.addMember(member);

        // 新增完成,準備轉交
        List<Member> list = memberSvc.getAll(); // 獲取所有會員資料
        model.addAttribute("memberListData", list); // 添加會員列表到模型
        model.addAttribute("success", "- (新增成功)"); // 添加成功信息到模型
        return "redirect:/member/listAllMember"; // 重定向到會員列表頁面
    }

    /*
     * 處理從列表頁面查詢特定會員的 POST 請求
     */
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("memberID") String memberID, ModelMap model) {
        // 開始查詢資料
        Member member = memberSvc.getOneMember(Integer.valueOf(memberID)); // 根據 memberID 查詢會員

        // 查詢完成,準備轉交
        model.addAttribute("member", member); // 添加會員資料到模型
        return "back-end/member/update_member_input"; // 返回更新頁面
    }

    /*
     * 處理更新會員資料的 POST 請求並驗證用戶輸入
     */
    @PostMapping("update")
    public String update(@Valid Member member, BindingResult result, ModelMap model,
                         @RequestParam("personalPhotos") MultipartFile[] parts) throws IOException {

        // 去除 BindingResult 中 personalPhotos 欄位的 FieldError 紀錄
        result = removeFieldError(member, result, "personalPhotos");

        if (parts[0].isEmpty()) { // 如果使用者未選擇新圖片
            byte[] personalPhotos = memberSvc.getOneMember(member.getMemberID()).getPersonalPhotos();
            member.setPersonalPhotos(personalPhotos); // 保留原圖片
        } else {
            for (MultipartFile multipartFile : parts) {
                byte[] personalPhotos = multipartFile.getBytes();
                member.setPersonalPhotos(personalPhotos); // 設置新圖片
            }
        }
        if (result.hasErrors()) { // 如果有錯誤
            return "back-end/member/update_member_input"; // 返回更新頁面
        }

        // 開始修改資料
        memberSvc.updateMember(member);

        // 修改完成,準備轉交
        model.addAttribute("success", "- (修改成功)"); // 添加成功信息到模型
        member = memberSvc.getOneMember(Integer.valueOf(member.getMemberID())); // 重新查詢更新後的會員資料
        model.addAttribute("member", member); // 添加會員資料到模型
        return "back-end/member/listOneMember"; // 返回單個會員資料頁面
    }

    /*
     * 處理刪除會員的 POST 請求
     */
    @PostMapping("delete")
    public String delete(@RequestParam("memberID") String memberID, ModelMap model) {
        // 開始刪除資料
        memberSvc.deleteMember(Integer.valueOf(memberID)); // 根據 memberID 刪除會員

        // 刪除完成,準備轉交
        List<Member> list = memberSvc.getAll(); // 獲取所有會員資料
        model.addAttribute("memberListData", list); // 添加會員列表到模型
        model.addAttribute("success", "- (刪除成功)"); // 添加成功信息到模型
        return "back-end/member/listAllMember"; // 返回會員列表頁面
    }

    /*
     * 處理複合查詢的 POST 請求
     */
    @PostMapping("listMembers_ByCompositeQuery")
    public String listAllMember(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap(); // 獲取請求參數
        List<Member> list = memberSvc.getAll(map); // 根據請求參數進行查詢
        model.addAttribute("memberListData", list); // 添加查詢結果到模型
        return "back-end/member/listAllMember"; // 返回會員列表頁面
    }

    /*
     * 處理查看會員詳細資料的 GET 請求
     */
    @GetMapping("/details")
    public String getMemberDetails(@RequestParam("memberID") String memberID, Model model) {
        Member member = memberSvc.getOneMember(Integer.valueOf(memberID));
        model.addAttribute("member", member);
        return "back-end/member/memberDetails"; // 返回會員詳細資料頁面
    }

    /*
     * 去除 BindingResult 中某個欄位的 FieldError 紀錄
     */
    public BindingResult removeFieldError(Member member, BindingResult result, String removedFieldname) {
        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
                .filter(fieldError -> !fieldError.getField().equals(removedFieldname))
                .collect(Collectors.toList()); // 過濾掉指定欄位的錯誤
        result = new BeanPropertyBindingResult(member, "member"); // 創建新的 BindingResult
        for (FieldError fieldError : errorsListToKeep) {
            result.addError(fieldError); // 添加過濾後的錯誤
        }
        return result; // 返回更新後的 BindingResult
    }
}
