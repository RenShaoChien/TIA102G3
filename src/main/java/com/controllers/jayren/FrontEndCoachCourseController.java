package com.controllers.jayren;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.service.CourseOrderService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * ClassName： FrontEndCoachCourseController
 * package：com.controllers.jayren
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/13 下午2:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/trainers")
public class FrontEndCoachCourseController {

    @Autowired
    private CoachCourseServiceImpl ccService;
    @Autowired
    private CourseOrderService coService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/currCoachCourse")
    public String currCoachCourse(@RequestParam("id") Integer courseID, ModelMap model) {
        ccService.findOneAllAttr(courseID).ifPresent(course -> model.put("currCourse", course));
        return "trainers/coachCourse";
    }

    @RequestMapping(value = "/orderCoachCourse", method = {RequestMethod.GET, RequestMethod.POST})
    public String orderCoachCourse(@RequestParam("coachCourseID") Integer courseID, ModelMap model, HttpSession session) {
        Object memberObj = session.getAttribute("user");
        if (memberObj == null) {
            session.setAttribute("pendingCourseID", courseID);
            return "redirect:/login";
        }

        Member member = (Member) memberObj;
        Optional<CoachCourse> optionalCoachCourse = ccService.findOneAllAttr(courseID);
        CoachCourse coachCourse = optionalCoachCourse.get();
        CourseOrder courseOrder = new CourseOrder(member, coachCourse);

        model.putAll(Map.of("courseOrder", courseOrder));

        return "trainers/course_order_checking";
    }

    @PostMapping("/orderCoachCourse.do")
    @ResponseBody
    public ResponseEntity orderCoachCourse(@RequestParam("coachCourseID") Integer courseID) {
        if (courseID != null) {
            CoachCourse orderCoachCourse = ccService.getOneOrderCoachCourse(courseID);
            return ResponseEntity.ok(orderCoachCourse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Coach Course Found in Request Scope");
        }
    }

    @PostMapping("/paying")
    public String coachCoursePaying(@Param("courseOrder") CourseOrder courseOrder, ModelMap model) {
        memberService.updateMemberByCourseOrder(courseOrder.getMember());
        courseOrder.setMember(memberService.findById(courseOrder.getMember().getMemberID()));
        ccService.findOneAllAttr(courseOrder.getCoachCourse().getId()).ifPresent(coachCourse -> courseOrder.setCoachCourse(coachCourse));
        model.putAll(Map.of("courseOrder", courseOrder));
        return "trainers/create_card";
    }

    @PostMapping("/paying.do")
    @Transactional
    public String processPayment(@ModelAttribute("courseOrder") CourseOrder courseOrder, ModelMap model){
        boolean paymentSuccess = simulatePaymentProcessing(courseOrder);
        if(paymentSuccess){
            memberService.saveMember(courseOrder.getMember());
            courseOrder.setStatus(1);
            courseOrder.setOrderDate(Date.valueOf(LocalDate.now()));
            courseOrder.setPrice(courseOrder.getCoachCourse().getCoursePrice());
            coService.addCourseOrder(courseOrder);
            model.putAll(Map.of("message", "完成付款，購買成功 ！", "currCourse", courseOrder.getCoachCourse()));
            return "trainers/coachCourse";
        }
        model.putAll(Map.of("errorMessage", "支付失敗，請重試或聯絡客服。", "courseOrder", courseOrder));
        return "trainers/create_card";
    }

    private boolean simulatePaymentProcessing(CourseOrder courseOrder) {
        // 模擬支付處理過程，假設成功返回 true
        // 在真實應用中，這裡可以是調用支付網關的 API 或其他支付邏輯
        System.out.println("Processing payment for CourseOrder: " + courseOrder.getCourseOrderID());

        // 模擬處理延遲
        try {
            Thread.sleep(1000); // 模擬一秒的支付處理時間
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        // 模擬隨機支付成功與否
        return Math.random() > 0.1;
    }

}
