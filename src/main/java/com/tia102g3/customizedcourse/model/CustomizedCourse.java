package com.tia102g3.customizedcourse.model;

import com.tia102g3.member.model.MemberVO;
import com.tia102g3.systemcourse.model.SystemCourse;
import lombok.*;

import javax.persistence.*;

/**
 * ClassName： CustomizedCourse
 * package：com.tia102g3.customizedcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "customized_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@SuppressWarnings("all")
//@NamedQuery(name = "CustomizedCourse.findAll", query = "SELECT c FROM CustomizedCourse c")
public class CustomizedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customizedCourseID", nullable = false)
    private Integer customizedCourseID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemCourseID", nullable = false, referencedColumnName = "systemCourseID")
    private SystemCourse systemCourse;
        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID", nullable = false, referencedColumnName = "memberID")
    private MemberVO member;
}
