package com.tia102g3.restifo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tia102g3.restifo.model.RestIfoService;
import com.tia102g3.restifo.model.RestIfoVO;
import com.tia102g3.restmap.model.RestMapService;
import com.tia102g3.restmap.model.RestMapVO;

@Controller
public class MapController {

	@Autowired
	private RestMapService restMapService;

	@Autowired
	private RestIfoService restIfoService;

	@GetMapping("/map")
	public String index(Model model) {
		// 從資料庫獲取所有 RestMapVO 並添加到模型中
	    List<RestMapVO> restMapList = restMapService.getAll();
	    List<List<Object>> fList = restMapList.stream().map((e) -> {
	        List<Object> temp = new ArrayList<>();
	        // 檢查 mapLat 是否為 null，如果是，設置為預設值
	        Double mapLat = e.getMapLat() != null ? e.getMapLat() : 0.0;
	        Double mapLng = e.getMapLng() != null ? e.getMapLng() : 0.0;
	        
	        temp.add(mapLat); // 將 mapLat 轉換為 double
	        temp.add(mapLng); // 將 mapLng 轉換為 double
	        temp.add(e.getMapID() != null ? Integer.parseInt(e.getMapID().toString()) : 0); // 將 mapID 轉換為 int，設置預設值為 0
	        return temp;
	    }).toList();
	    model.addAttribute("restMapList", fList);

	    // 獲取所有 RestIfoVO 並添加到模型中
	    List<RestIfoVO> restIfoListData = restIfoService.getAll();
	    model.addAttribute("restIfoListData", restIfoListData);

	    return "map"; // 導向 map.html 模板
}
	}