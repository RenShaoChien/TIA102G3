import com.Application;
import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.sportevent.model.SportEventDAOImpl;
import com.tia102g3.systemcourse.model.SystemCourseDAOImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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
    private SportEventDAOImpl sportEventDAO;
    @Autowired
    private SystemCourseDAOImpl systemCourseDAO;
    @Test
    public void testJayRen() throws Exception {
        System.out.println(sportEventDAO.selectSportEventByID(1));
    }
    @Test
    public void testSportEventgetAll() throws Exception {
        for (SportEvent sportEvent : sportEventDAO.selectAllSportEvents()) {
            System.out.println("sportEvent = " + sportEvent);
        }

    }
    @Test
    public void testSystemCourseGetAll() {
        try {
            System.out.println(systemCourseDAO.getAllSystemCourses());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
