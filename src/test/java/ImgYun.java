import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;

import com.Application;
import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductVO;


/**
 * ClassName： ImgYun
 * package：PACKAGE_NAME
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/15 下午2:54
 * @Version 1.0
 */
@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class ImgYun {
	
	@Autowired
    ProductRepository pr;


    @Test
    public void pic() throws IOException {


        ClassPathResource resource1 = new ClassPathResource("static/back_end_img/product/01.png");
        ClassPathResource resource2 = new ClassPathResource("static/back_end_img/product/02.png");
        ClassPathResource resource3 = new ClassPathResource("static/back_end_img/product/03.png");
        ClassPathResource resource4 = new ClassPathResource("static/back_end_img/product/04.png");
        ClassPathResource resource5 = new ClassPathResource("static/back_end_img/product/05.png");
        ClassPathResource resource6 = new ClassPathResource("static/back_end_img/product/06.png");
        ClassPathResource resource7 = new ClassPathResource("static/back_end_img/product/07.png");       
        ClassPathResource resource8 = new ClassPathResource("static/back_end_img/product/08.png");
        ClassPathResource resource9 = new ClassPathResource("static/back_end_img/product/09.png");
        ClassPathResource resource10 = new ClassPathResource("static/back_end_img/product/10.png");
        ClassPathResource resource11 = new ClassPathResource("static/back_end_img/product/11.png");
        ClassPathResource resource12 = new ClassPathResource("static/back_end_img/product/12.png");
        

        pr.save(new ProductVO(1, "高蛋白能量棒" , 100, 1000, "由專業營養師團隊研發，專為健身人士設計，滿足高蛋白低熱量的需求。", Files.readAllBytes(resource1.getFile().toPath())));
        pr.save(new ProductVO(2, "好好吃能量棒", 100, 1000, "兼具美味與營養，運動後還是可以擁有吃甜點的幸福感！。", Files.readAllBytes(resource2.getFile().toPath())));
        pr.save(new ProductVO(3, "高蛋白豆腐棒", 150, 1000, "素食者的增肌好夥伴，由日本高湯滷製而成。是飢餓解饞的好選擇！" ,Files.readAllBytes(resource3.getFile().toPath())));
        pr.save(new ProductVO(4, "營養機能食品", 300, 500, "德國進口，緩解飲食不均衡的問題。", Files.readAllBytes(resource4.getFile().toPath())));
        pr.save(new ProductVO(5, "機能性表示食品", 400, 500,"日本進口，加強吸收每日所需的營養素。", Files.readAllBytes(resource5.getFile().toPath())));
        pr.save(new ProductVO(6, "特定保健食品", 500, 500,"為健身人士製造，針對運動傷害所設計的保健食品。", Files.readAllBytes(resource6.getFile().toPath())));
        pr.save(new ProductVO(7, "好健康雞腿棒", 100, 200, "不但要吃得健康，也要吃的開心！好健康雞腿棒讓你吃的滿足又健康。", Files.readAllBytes(resource7.getFile().toPath())));
        pr.save(new ProductVO(8, "偷偷吃點心球", 100, 1000, "真的嘴饞到撐不下去時可以吃，它的熱量不會太高...", Files.readAllBytes(resource8.getFile().toPath())));
        pr.save(new ProductVO(9, "瘦身茉莉花茶", 50, 500, "今天要吃大餐嗎？記得買一瓶解油去膩！", Files.readAllBytes(resource9.getFile().toPath())));
        pr.save(new ProductVO(10, "瘦身蘋果醋", 100, 500, "在日本OL間盛行的喝醋減肥法，是小資女們的瘦身首選。", Files.readAllBytes(resource10.getFile().toPath())));
        pr.save(new ProductVO(11, "冰涼涼運動飲料", 50, 200, "運動後滿頭大汗，冰涼涼運動飲料解暑又能補充礦物質", Files.readAllBytes(resource11.getFile().toPath())));
        pr.save(new ProductVO(12, "好營養高鈣鮮奶", 200, 200, "補充蛋白質與鈣質，運用最新科技提高鈣質含量。", Files.readAllBytes(resource12.getFile().toPath())));
    }


}
