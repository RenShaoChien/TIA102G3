package com.controllers.finn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.restifo.model.RestIfoService;
import com.tia102g3.restifo.model.RestIfoVO;
import com.tia102g3.restmap.model.RestMapService;

@Controller
public class RestIfoPageController {
	
	@Autowired
    RestIfoService restIfoSvc;
	
	@Autowired
    RestMapService restMapSvc;

    @GetMapping("/restifo/select_page")
    public String select_page(Model model) {
        return "back-end/restifo/select_page";
    }

    @GetMapping("/restifo/listAllRestIfo")
    public String listAllRestIfo(Model model) {
    	List<RestIfoVO> restIfos = restIfoSvc.getAll();
        model.addAttribute("restIfos", restIfos);
        return "back-end/restifo/listAllRestIfo";
    }

    @ModelAttribute("restIfoListData") 
    protected List<RestIfoVO> referenceListData(Model model) {
        List<RestIfoVO> list = restIfoSvc.getAll();  
        return list;
    }
	

}
