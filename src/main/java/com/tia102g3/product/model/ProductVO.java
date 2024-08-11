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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message = "商品名稱不能為空")
	private String prodName;
	
	@NotNull(message = "商品價格不能為空")
	private Integer price;
	
	@NotNull(message = "商品數量不能為空")
	private Integer productQuantity;
	
	@NotBlank(message = "商品簡介不能為空")
	private String intro;
	
	@Column(name = "productPic", columnDefinition = "longblob")
	private byte[] productPic;

//	public byte[] setProductPic;

}
