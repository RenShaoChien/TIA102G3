package com.tia102g3.systemcoursepic.service;

import com.tia102g3.systemcoursepic.model.SystemCoursePic;

/**
 * ClassName： SystemCoursePicService
 * package：com.tia102g3.systemcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCoursePicService {
    /**
     * 新增圖片
     * @param systemCoursePic
     * @return
     * @throws Exception
     */
    int insertSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception;

    /**
     * 刪除圖片
     * @param systemCoursePicID
     * @return
     * @throws Exception
     */
    int deleteSystemCoursePicByID(Integer systemCoursePicID) throws Exception;

    /**
     * 換圖片
     * @param systemCoursePic
     * @return
     * @throws Exception
     */
    int updateSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception;

    /**
     * 查詢圖片
     * @param systemCoursePicID
     * @return
     * @throws Exception
     */
    SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID) throws Exception;
}
