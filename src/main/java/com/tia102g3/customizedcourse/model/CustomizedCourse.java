package com.tia102g3.customizedcourse.model;

import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.member.model.Member;
import com.tia102g3.systemcourse.model.SystemCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer customizeCourseID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemCourseID", nullable = false, referencedColumnName = "systemCourseID")
    @ForeignKey(targetEntity = SystemCourse.class, keyField = "systemCourseID")
    private SystemCourse systemCourse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID", nullable = false, referencedColumnName = "memberID")
    @ForeignKey(targetEntity = Member.class, keyField = "memberID")
    private Member member;
}
