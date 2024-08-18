import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;

import com.Application;
import com.tia102g3.coachmember.model.CoachMemberRepository;
import com.tia102g3.member.model.MemberRepository;

@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class ImgDavid {
	
	@Autowired
	MemberRepository mr;
	
	@Autowired
	CoachMemberRepository cmr;
	
	// 一般會員更新
	@Test
	public void updateMembers() throws IOException {
	    ClassPathResource photoResource1 = new ClassPathResource("static/back_end_img/david/1.jpg");
	    ClassPathResource photoResource2 = new ClassPathResource("static/back_end_img/david/2.jpg");
	    ClassPathResource photoResource3 = new ClassPathResource("static/back_end_img/david/3.jpg");
	    ClassPathResource photoResource4 = new ClassPathResource("static/back_end_img/david/4.jpg");
	    ClassPathResource photoResource5 = new ClassPathResource("static/back_end_img/david/5.jpg");
	    ClassPathResource photoResource6 = new ClassPathResource("static/back_end_img/david/6.jpg");
	    ClassPathResource photoResource7 = new ClassPathResource("static/back_end_img/david/7.jpg");
	    ClassPathResource photoResource8 = new ClassPathResource("static/back_end_img/david/8.jpg");
	    ClassPathResource photoResource9 = new ClassPathResource("static/back_end_img/david/9.jpg");
	    ClassPathResource photoResource10 = new ClassPathResource("static/back_end_img/david/10.jpg");
	    ClassPathResource photoResource11 = new ClassPathResource("static/back_end_img/david/11.jpg");
	    ClassPathResource photoResource12 = new ClassPathResource("static/back_end_img/david/12.jpg");

	    mr.updateMember(Files.readAllBytes(photoResource1.getFile().toPath()), 1);
        mr.updateMember(Files.readAllBytes(photoResource2.getFile().toPath()), 2);
        mr.updateMember(Files.readAllBytes(photoResource3.getFile().toPath()), 3);
        mr.updateMember(Files.readAllBytes(photoResource4.getFile().toPath()), 4);
        mr.updateMember(Files.readAllBytes(photoResource5.getFile().toPath()), 5);
        mr.updateMember(Files.readAllBytes(photoResource6.getFile().toPath()), 6);
        mr.updateMember(Files.readAllBytes(photoResource7.getFile().toPath()), 7);
        mr.updateMember(Files.readAllBytes(photoResource8.getFile().toPath()), 8);
        mr.updateMember(Files.readAllBytes(photoResource9.getFile().toPath()), 9);
        mr.updateMember(Files.readAllBytes(photoResource10.getFile().toPath()), 10);
        mr.updateMember(Files.readAllBytes(photoResource11.getFile().toPath()), 11);
        mr.updateMember(Files.readAllBytes(photoResource12.getFile().toPath()), 12);
	}
	
	// 教練會員更新
	@Test
	public void updateCoachMembers() throws IOException {
	    ClassPathResource photoResource1 = new ClassPathResource("static/back_end_img/david/1.jpg");
	    ClassPathResource photoResource2 = new ClassPathResource("static/back_end_img/david/2.jpg");
	    ClassPathResource photoResource3 = new ClassPathResource("static/back_end_img/david/3.jpg");
	    ClassPathResource photoResource4 = new ClassPathResource("static/back_end_img/david/4.jpg");
	    ClassPathResource photoResource5 = new ClassPathResource("static/back_end_img/david/5.jpg");

	    cmr.updateCoachMember(Files.readAllBytes(photoResource1.getFile().toPath()), 1);
	    cmr.updateCoachMember(Files.readAllBytes(photoResource2.getFile().toPath()), 2);
	    cmr.updateCoachMember(Files.readAllBytes(photoResource3.getFile().toPath()), 3);
	    cmr.updateCoachMember(Files.readAllBytes(photoResource4.getFile().toPath()), 4);
	    cmr.updateCoachMember(Files.readAllBytes(photoResource5.getFile().toPath()), 5);
	}
}
