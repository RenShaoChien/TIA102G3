package com.controllers.finn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.admin.model.AdminVO;  
import com.tia102g3.admin.model.AdminService;

@Controller
public class AdminPageController {
    
    @Autowired
    AdminService adminSvc;

    @GetMapping("/admin/select_page")
    public String select_page(Model model) {
        return "back-end/admin/select_page";
    }

    @GetMapping("/admin/listAllAdmin")
    public String listAllAdmin(Model model) {
        return "back-end/admin/listAllAdmin";
    }

    @ModelAttribute("adminListData") 
    protected List<AdminVO> referenceListData(Model model) {
        List<AdminVO> list = adminSvc.getAll();  
        return list;
    }
}
