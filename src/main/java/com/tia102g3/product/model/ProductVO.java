package com.tia102g3.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.systemcourse.model.SystemCourseLevel;
@Entity
@Table(name="product")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@NonNull
	private Integer productID;
	
	private String prodName;
	
	private Integer price;
	
	private Integer productQuantity;
	
	private String intro;
	
	@Column(name = "productPic", columnDefinition = "longblob")
	private byte[] productPic;

//	public byte[] setProductPic;

}
