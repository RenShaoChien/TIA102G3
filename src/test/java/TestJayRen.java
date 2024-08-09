import com.Application;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClassName： TestJayRen
 * package：PACKAGE_NAME
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/1 下午7:57
 * @Version 1.0
 */
@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class TestJayRen {
    @Autowired
    CoachCoursePicDAO ccpDAO;

    @Test
    public void testInsert() throws IOException {
        File file = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south1.jpeg");
        File file1 = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south2.jpeg");
        File file2 = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south3.jpeg");
        FileInputStream fis = new FileInputStream(file);
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);
        byte[] bytes = fis.readAllBytes();
        byte[] bytes1 = fis1.readAllBytes();
        byte[] bytes2 = fis2.readAllBytes();
        CoachCoursePic ccp = new CoachCoursePic();
        ccp.setPic(bytes);
        ccp.setCoachCourse(new CoachCourse(1));
        CoachCoursePic ccp1 = new CoachCoursePic();
        ccp1.setPic(bytes1);
        ccp1.setCoachCourse(new CoachCourse(1));
        CoachCoursePic ccp2= new CoachCoursePic();
        ccp2.setPic(bytes2);
        ccp2.setCoachCourse(new CoachCourse(1));
        ccpDAO.save(ccp);
        ccpDAO.save(ccp1);
        ccpDAO.save(ccp2);
    }
}
