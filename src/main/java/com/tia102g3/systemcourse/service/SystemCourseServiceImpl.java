package com.tia102g3.systemcourse.service;

import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.model.SystemCourseDAO;
import com.tia102g3.systemcourse.model.SystemCourseLevel;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import com.tia102g3.systemcoursepic.model.SystemCoursePicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * ClassName： SystemCourseServiceImpl
 * package：com.tia102g3.systemcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Service
public class SystemCourseServiceImpl implements SystemCourseService {
    @Autowired
    private SystemCourseDAO systemCourseDAO;
    @Autowired
    private SystemCoursePicDAO systemCoursePicDAO;

    @Override
    @Transactional
    public void insertSystemCourse(SystemCourse systemCourse, MultipartFile[] courseImages) throws IOException {
        systemCourseDAO.save(systemCourse);
        if (systemCourse.getSystemCourseID() == null) {
            throw new IllegalStateException("SystemCourse ID should not be null after saving");
        }
        if (courseImages.length > 3) {
            throw new IllegalArgumentException("最多上傳三張圖片");
        }
        for (MultipartFile file : courseImages) {
            if (!file.isEmpty()) {
                systemCoursePicDAO.save(new SystemCoursePic(systemCourse, file));
            }
        }
    }


    @Override
    @Transactional
    public void updateSystemCourse(SystemCourse systemCourse, MultipartFile[] courseImages) throws IOException {
        systemCourseDAO.save(systemCourse);
        if (courseImages.length > 3) {
            throw new IllegalArgumentException("最多上傳三張圖片");
        }
        boolean hasNewImages = false;
        for (MultipartFile file : courseImages) {
            if (!file.isEmpty()) {
                hasNewImages = true;
                break;
            }
        }
        if (hasNewImages) {
            systemCoursePicDAO.delBySystemCourseID(systemCourse.getSystemCourseID());
            for (MultipartFile file : courseImages) {
                if (!file.isEmpty()) {
                    systemCoursePicDAO.save(new SystemCoursePic(systemCourse, file));
                }
            }
        }
    }


    @Override
    @Transactional
    public Long getSystemCourseCount(String keyword) {
        return systemCourseDAO.getSystemCourseCount(keyword);
    }

    @Override
    @Transactional
    public List<SystemCourse> getSystemCoursesByReqPara(String sportTypes, String sportEventName, String sportEquipment, String keyword, String courseLevel) {

        List<SystemCourse> listByReqPara;

        if (!(listByReqPara = systemCourseDAO.getListByReqPara(sportTypes, sportEventName, sportEquipment, keyword, SystemCourseLevel.fromDescription(courseLevel))).isEmpty()) {
            return listByReqPara;
        }
        if (!(listByReqPara = systemCourseDAO.getListByReqPara(sportTypes, sportEventName, sportEquipment, keyword)).isEmpty()){
            return listByReqPara;
        }
        if (!(listByReqPara = systemCourseDAO.getListByReqPara(sportTypes, sportEventName)).isEmpty()){
            return listByReqPara;
        }
        return List.of();
    }

    @Override
    @Transactional
    public int deleteSystemCourse(Integer systemCourseID){
        return systemCourseDAO.deleteSystemCourse(systemCourseID);
    }

    @Override
    @Transactional
    public SystemCourse getSystemCourseById(Integer systemCourseID){
        return systemCourseDAO.getReferenceById(systemCourseID);
    }

    @Override
    @Transactional
    public List<SystemCourse> getSystemCoursesList(String keyword, Integer pageNo)  {
        List<Object[]> rawData = systemCourseDAO.getSystemCoursesList(keyword, pageNo);
        System.out.println(rawData.size());
        return rawData.stream()
                .map(this::convertToSystemCourse)
                .toList();
    }
    private SystemCourse convertToSystemCourse(Object[] row) {
        SystemCourse course = new SystemCourse();
        course.setSystemCourseID((Integer) row[0]);
        course.setCourseName((String) row[1]);
        course.setSportEventName((String) row[2]);
        course.setSportTypes((String) row[3]);
        course.setSportEquipment((String) row[4]);
        course.setCourseLevel((SystemCourseLevel.getSystemCourseLevelByInt((Integer) row[5])));
        course.setBurnCalories((Integer) row[6]);
        course.setRps((Integer) row[7]);
        course.setEachExerciseTime((String) row[8]);
        course.setSportTime((String) row[9]);
        course.setSwp((Integer) row[10]);
        course.setIllustrate((String) row[11]);
        course.setVideo((String) row[12]);
        return course;
    }

    @Override
    @Transactional
    public List<SystemCoursePic> getSystemCoursePicsBySystemCourseId(Integer systemCourseID) {
        SystemCourse systemCourse = systemCourseDAO.findById(systemCourseID).orElse(null);
        if (systemCourse != null) {
            return systemCourse.getSystemCoursePics();
        }
        return null;
    }


}


