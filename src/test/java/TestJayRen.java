
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import com.Application;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductVO;
import com.tia102g3.systemcourse.model.SystemCourseLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.Application;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductVO;

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
    
    @Autowired
    ProductRepository pr;
    @Autowired
    ProductRepository dao;


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

   

    @Test
    public void testInsert2() throws IOException {
        File file = new File("C:\\Users\\T14 Gen 3\\Desktop\\專題\\dogfood.png");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        ProductVO pvo = new ProductVO();
        pvo.setProductPic(bytes);
//        ppic.setProductVO(new CoachCourse(21));       
        pr.save(pvo);
    }


    @Test
    public void testProduct() throws IOException {
        File file1 = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south1.jpeg");
        File file = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south2.jpeg");
        File file2 = new File("C:\\Users\\monke.JAYRENSCAR16\\Desktop\\forproject\\south3.jpeg");
        FileInputStream fis = new FileInputStream(file);
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);
        byte[] bytes = fis.readAllBytes();
        byte[] bytes1 = fis1.readAllBytes();
        byte[] bytes2 = fis2.readAllBytes();
        dao.save(new ProductVO(1,"test", 100, 1000, "415456", bytes));
        dao.save(new ProductVO(2,"test1", 200, 2000, "415457", bytes1));
        dao.save(new ProductVO(3,"test2", 300, 3000, "415458", bytes2));
    }

    @Test
    public void test() {
        System.out.println(SystemCourseLevel.fromDescription("最高級"));
    }

}
