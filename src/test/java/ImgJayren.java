import com.Application;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import com.tia102g3.systemcoursepic.model.SystemCoursePicDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ClassName： ImgJayren
 * package：PACKAGE_NAME
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/15 下午2:54
 * @Version 1.0
 */
@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class ImgJayren {
    @Autowired
    SystemCoursePicDAO dao;
    @Autowired
    CoachCoursePicDAO dao1;

    @Test
    public void pic() throws IOException {


        ClassPathResource resource1 = new ClassPathResource("static/back_end_img/jayren/1-1.jpeg");
        ClassPathResource resource2 = new ClassPathResource("static/back_end_img/jayren/1-2.jpg");
        ClassPathResource resource3 = new ClassPathResource("static/back_end_img/jayren/1-3.jpeg");
        ClassPathResource resource4 = new ClassPathResource("static/back_end_img/jayren/1-4.jpg");
        ClassPathResource resource5 = new ClassPathResource("static/back_end_img/jayren/2-1.png");
        ClassPathResource resource6 = new ClassPathResource("static/back_end_img/jayren/2-2.jpg");
        ClassPathResource resource7 = new ClassPathResource("static/back_end_img/jayren/2-3.png");
        ClassPathResource resource8 = new ClassPathResource("static/back_end_img/jayren/3-1.jpg");
        ClassPathResource resource9 = new ClassPathResource("static/back_end_img/jayren/3-2.png");
        ClassPathResource resource10 = new ClassPathResource("static/back_end_img/jayren/4-1.jpg");
        ClassPathResource resource11 = new ClassPathResource("static/back_end_img/jayren/5-1.png");
        ClassPathResource resource12 = new ClassPathResource("static/back_end_img/jayren/5-2.jpg");
        ClassPathResource resource13 = new ClassPathResource("static/back_end_img/jayren/6-1.png");
        ClassPathResource resource14 = new ClassPathResource("static/back_end_img/jayren/7-1.jpg");
        ClassPathResource resource15 = new ClassPathResource("static/back_end_img/jayren/8-1.jpg");
        ClassPathResource resource16 = new ClassPathResource("static/back_end_img/jayren/8-2.png");
        ClassPathResource resource17 = new ClassPathResource("static/back_end_img/jayren/8-3.png");
        ClassPathResource resource18 = new ClassPathResource("static/back_end_img/jayren/9-1.png");
        ClassPathResource resource19 = new ClassPathResource("static/back_end_img/jayren/9-2.png");
        ClassPathResource resource20 = new ClassPathResource("static/back_end_img/jayren/10-1.png");
        ClassPathResource resource21 = new ClassPathResource("static/back_end_img/jayren/10-2.png");

        dao.save(new SystemCoursePic(1, new SystemCourse(1), Files.readAllBytes(resource1.getFile().toPath())));
        dao.save(new SystemCoursePic(2, new SystemCourse(1), Files.readAllBytes(resource2.getFile().toPath())));
        dao.save(new SystemCoursePic(3, new SystemCourse(1), Files.readAllBytes(resource3.getFile().toPath())));
        dao.save(new SystemCoursePic(4, new SystemCourse(1), Files.readAllBytes(resource4.getFile().toPath())));
        dao.save(new SystemCoursePic(5, new SystemCourse(2), Files.readAllBytes(resource5.getFile().toPath())));
        dao.save(new SystemCoursePic(6, new SystemCourse(2), Files.readAllBytes(resource6.getFile().toPath())));
        dao.save(new SystemCoursePic(7, new SystemCourse(2), Files.readAllBytes(resource7.getFile().toPath())));
        dao.save(new SystemCoursePic(8, new SystemCourse(3), Files.readAllBytes(resource8.getFile().toPath())));
        dao.save(new SystemCoursePic(9, new SystemCourse(3), Files.readAllBytes(resource9.getFile().toPath())));
        dao.save(new SystemCoursePic(10, new SystemCourse(4), Files.readAllBytes(resource10.getFile().toPath())));
        dao.save(new SystemCoursePic(11, new SystemCourse(5), Files.readAllBytes(resource11.getFile().toPath())));
        dao.save(new SystemCoursePic(12, new SystemCourse(5), Files.readAllBytes(resource12.getFile().toPath())));
        dao.save(new SystemCoursePic(13, new SystemCourse(6), Files.readAllBytes(resource13.getFile().toPath())));
        dao.save(new SystemCoursePic(14, new SystemCourse(7), Files.readAllBytes(resource14.getFile().toPath())));
        dao.save(new SystemCoursePic(15, new SystemCourse(8), Files.readAllBytes(resource15.getFile().toPath())));
        dao.save(new SystemCoursePic(16, new SystemCourse(8), Files.readAllBytes(resource16.getFile().toPath())));
        dao.save(new SystemCoursePic(17, new SystemCourse(8), Files.readAllBytes(resource17.getFile().toPath())));
        dao.save(new SystemCoursePic(18, new SystemCourse(9), Files.readAllBytes(resource18.getFile().toPath())));
        dao.save(new SystemCoursePic(19, new SystemCourse(9), Files.readAllBytes(resource19.getFile().toPath())));
        dao.save(new SystemCoursePic(20, new SystemCourse(10), Files.readAllBytes(resource20.getFile().toPath())));
        dao.save(new SystemCoursePic(21, new SystemCourse(10), Files.readAllBytes(resource21.getFile().toPath())));

    }


    @Test
    public void test2() throws IOException {
        Random rd = new Random();

        ClassPathResource resource22 = new ClassPathResource("static/back_end_img/jayren/01.jpg");
        ClassPathResource resource23 = new ClassPathResource("static/back_end_img/jayren/02.png");
        ClassPathResource resource24 = new ClassPathResource("static/back_end_img/jayren/03.jpg");
        ClassPathResource resource25 = new ClassPathResource("static/back_end_img/jayren/04.jpeg");
        ClassPathResource resource26 = new ClassPathResource("static/back_end_img/jayren/05.png");
        ClassPathResource resource27 = new ClassPathResource("static/back_end_img/jayren/06.jpg");
        ClassPathResource resource28 = new ClassPathResource("static/back_end_img/jayren/07.png");

        List<ClassPathResource> pic = Arrays.asList(resource22, resource23, resource24, resource25, resource26, resource27, resource28);
        for (int i = 0; i < 20; i++) {
            dao1.save(new CoachCoursePic(i + 1, new CoachCourse(i + 1), Files.readAllBytes(pic.get(rd.nextInt(pic.size())).getFile().toPath())));
        }

    }
}