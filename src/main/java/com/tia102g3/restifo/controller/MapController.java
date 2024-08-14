package com.tia102g3.restifo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tia102g3.restifo.model.RestIfoService;
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
			// 將 mapID 轉換為 int
			temp.add(Double.parseDouble(e.getMapLat().toString())); // 將 mapLat 轉換為 double
			temp.add(Double.parseDouble(e.getMapLng().toString()));
			temp.add(Integer.parseInt(e.getMapID().toString()));
			return temp;
		}).toList();
		model.addAttribute("restMapList", fList);
		return "map"; // 導向 map.html 模板

	}

}
