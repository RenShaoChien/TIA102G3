package com.tia102g3.systemcoursepic.model;

/**
 * ClassName： SystemCoursePicDAO
 * package：com.tia102g3.systemcoursepic.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCoursePicDAO {
    /**
     * 增
     * @param systemCoursePic
     * @return
     */
    int insertSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception;

    /**
     * 刪
     * @param systemCoursePicID
     * @return
     */
    int deleteSystemCoursePicByID(Integer systemCoursePicID) throws Exception;

    /**
     * 改
     * @param systemCoursePic
     * @return
     */
    int updateSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception;

    /**
     * 查
     * @param systemCoursePicID
     * @return
     */
    SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID) throws Exception;
}
