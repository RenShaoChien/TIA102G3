package com.tia102g3.restifo.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g3.restifo.model.RestIfoService;
import com.tia102g3.restifo.model.RestIfoVO;
import com.tia102g3.restmap.model.RestMapRepository;
import com.tia102g3.restmap.model.RestMapService;
import com.tia102g3.restmap.model.RestMapVO;


@Controller
@RequestMapping("/restifo")
public class RestIfoController {

    @Autowired
    private RestIfoService restIfoSvc;

    @Autowired
    private RestMapService restMapSvc;

    @Autowired
    private RestMapRepository restMapRepository; // 添加RestMapRepository的注入

    /*
     * This method will serve as addRestIfo.html handler.
     */
    @GetMapping("addRestIfo")
    public String addRestIfo(ModelMap model) {
        RestIfoVO restIfoVO = new RestIfoVO();
        model.addAttribute("restIfoVO", restIfoVO);
        return "back-end/restifo/addRestIfo";
    }

    /*
     * This method will be called on addRstIfo.html form submission, handling POST request It also validates the user input
     */
    @PostMapping("insert")
    public String insert(@Valid RestIfoVO restIfoVO, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        // 檢查表單是否有錯誤
        if (result.hasErrors()) {
            return "back-end/restifo/addRestIfo";
        }

        /*************************** 2. 開始新增資料 *****************************************/
        // 先創建並保存新的 RestMapVO，並獲取自增的 mapID
//        RestMapVO restMapVO = new RestMapVO();
        restMapRepository.save(restIfoVO.getRestMapVO());  // 使用RestMapRepository的實例方法保存

        // 將新生成的 RestMapVO 設置到 RestIfoVO 中
//        restIfoVO.setRestMapVO(restMapVO);

        // 保存 RestIfoVO，它的 restLoc 會自動增長，並且與 mapID 關聯
        restIfoSvc.addRestIfo(restIfoVO);

        /*************************** 3. 新增完成, 準備轉交(Send the Success view) **************/
        // 取得所有 RestIfoVO 的列表並存入 model
        List<RestIfoVO> list = restIfoSvc.getAll();
        model.addAttribute("restIfoListData", list);
        model.addAttribute("success", "- (新增成功)");

        // 重導至列表頁面，顯示所有新增的資料
        return "redirect:/restifo/listAllRestIfo"; // 新增成功後重導至 IndexController_inSpringBoot.java 的第 58 行 @GetMapping("/restIfo/listAllRestIfo")
    }

	/*
	 * This method will be called on listAllRestIfo.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("restLoc") String restLoc, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// RestIfoService restIfoSvc = new RestIfoService();
		RestIfoVO restIfoVO = restIfoSvc.getOneRestIfo(Integer.valueOf(restLoc));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("restIfoVO", restIfoVO);
		return "back-end/restifo/update_restIfo_input"; // 查詢完成後轉交update_restIfo_input.html
	}

	/*
	 * This method will be called on update_restIfo_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid RestIfoVO restIfoVO, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "back-end/restifo/update_restIfo_input";
		}
		restIfoSvc.updateRestIfo(restIfoVO);
		model.addAttribute("success", "- (修改成功)");
		restIfoVO = restIfoSvc.getOneRestIfo(Integer.valueOf(restIfoVO.getRestLoc()));
		model.addAttribute("restIfoVO", restIfoVO);
		return "back-end/restifo/listOneRestIfo";
	}

	/*
	 * This method will be called on listAllRestIfo.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("restLoc") String restLoc, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		restIfoSvc.deleteRestIfo(Integer.valueOf(restLoc));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<RestIfoVO> list = restIfoSvc.getAll();
		model.addAttribute("restIfoListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/restifo/listAllRestIfo"; // 刪除完成後轉交listAllRestIfo.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="mapID" id="mapID" items="${restMapListData}" itemValue="mapID" itemLabel="mapLat" />
	 */
	@ModelAttribute("restMapListData")
	protected List<RestMapVO> referenceListData() {
		// RestMapService reatMapSvc = new RestMapService();
		List<RestMapVO> list = restMapSvc.getAll();
		return list;
	}


	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(RestIfoVO restIfoVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(restIfoVO, "restIfoVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("listRestIfos_ByCompositeQuery")
	public String listAllRestIfo(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<RestIfoVO> list = restIfoSvc.getAll(map);
		model.addAttribute("restIfoListData", list); // for listAllRestIfo.html 第85行用
		return "back-end/restifo/listAllRestIfo";
	}

}
