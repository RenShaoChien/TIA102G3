//package com.tia102g3.productPic.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.util.List;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.tia102g3.coachcourse.model.CoachCourse;
//import com.tia102g3.coachcourse.model.CourseStatus;
//import com.tia102g3.coachcoursepic.model.CoachCoursePic;
//import com.tia102g3.coachmember.model.CoachMember;
//import com.tia102g3.courseorder.model.CourseOrder;
//import com.tia102g3.product.model.ProductVO;
//import com.tia102g3.systemcourse.model.SystemCourseLevel;
//@Entity
//@Table(name="product_pic")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//public class Product_PICVO implements java.io.Serializable{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@NonNull
//	private Integer productPicID;
//	
//    @ManyToOne
//    @JoinColumn(name = "productID", referencedColumnName = "productID", nullable = false)
//	private ProductVO productID;
//	
//	private byte[] pic;
//
//
//}
