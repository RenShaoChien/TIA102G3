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

@Controller // 標識這個類為 Spring MVC 的控制器
public class MapController {

	@Autowired // 使用 Spring 的依賴注入將 RestMapService 和 RestIfoService 自動注入到這個控制器中
	private RestMapService restMapService;

	@Autowired
	private RestIfoService restIfoService;

	@GetMapping("/map") // 將 /map 的 GET 請求映射到這個方法
	public String index(Model model) {
		// 從資料庫獲取所有 RestMapVO 並添加到模型中
	    List<RestMapVO> restMapList = restMapService.getAll(); // 從 RestMapService 中調用 getAll 方法獲取所有地圖數據
	    List<List<Object>> fList = restMapList.stream().map((e) -> { // 使用 Java Stream API 來轉換 RestMapVO 物件列表
	        List<Object> temp = new ArrayList<>(); // 創建一個臨時的 List 用來存儲單個地圖數據的屬性
	        
	        // 檢查 mapLat 是否為 null，如果是，設置為預設值
	        Double mapLat = e.getMapLat() != null ? e.getMapLat() : 0.0; // 獲取地圖的緯度，如果為 null，則設置為 0.0
	        Double mapLng = e.getMapLng() != null ? e.getMapLng() : 0.0; // 獲取地圖的經度，如果為 null，則設置為 0.0
	        
	        temp.add(mapLat); // 將 mapLat 添加到臨時列表中
	        temp.add(mapLng); // 將 mapLng 添加到臨時列表中
	        temp.add(e.getMapID() != null ? Integer.parseInt(e.getMapID().toString()) : 0); // 將 mapID 轉換為 int 並添加到臨時列表中，如果為 null 則設置為 0
	        return temp; // 返回包含了地圖數據的臨時列表
	    }).toList(); // 將處理後的數據轉換為 List
	    
	    model.addAttribute("restMapList", fList); // 將處理好的地圖數據列表添加到模型中，供 Thymeleaf 用

	    // 獲取所有 RestIfoVO 並添加到模型中
	    List<RestIfoVO> restIfoListData = restIfoService.getAll(); // 從 RestIfoService 中調用 getAll 方法獲取所有餐廳信息數據
	    model.addAttribute("restIfoListData", restIfoListData); // 將餐廳信息列表添加到模型中，供 Thymeleaf用

	    return "map"; 
	}
}
