package com.tia102g3.course_order.model;

import java.sql.Date;

public class CourseOrderVO {

	    private int courseOrderID; // 課程訂單ID
	    private int memberID; // 會員ID
	    private int coachCourseID; // 教練課程ID
	    private Date orderDate; // 訂單日期
	    private int price; // 課程金額
	    private String status; // 訂單狀態

	    // 無參構造函數
	    public CourseOrderVO() {}

	    // 全參構造函數
	    public CourseOrderVO(int courseOrderID, int memberID, int coachCourseID, Date orderDate, int price, String status) {
	        this.courseOrderID = courseOrderID;
	        this.memberID = memberID;
	        this.coachCourseID = coachCourseID;
	        this.orderDate = orderDate;
	        this.price = price;
	        this.status = status;
	    }

	    // Getter 和 Setter 方法

	    public int getCourseOrderID() {
	        return courseOrderID;
	    }

	    public void setCourseOrderID(int courseOrderID) {
	        this.courseOrderID = courseOrderID;
	    }

	    public int getMemberID() {
	        return memberID;
	    }

	    public void setMemberID(int memberID) {
	        this.memberID = memberID;
	    }

	    public int getCoachCourseID() {
	        return coachCourseID;
	    }

	    public void setCoachCourseID(int coachCourseID) {
	        this.coachCourseID = coachCourseID;
	    }

	    public Date getOrderDate() {
	        return orderDate;
	    }

	    public void setOrderDate(Date orderDate) {
	        this.orderDate = orderDate;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "CourseOrder{" +
	                "courseOrderID=" + courseOrderID +
	                ", memberID=" + memberID +
	                ", coachCourseID=" + coachCourseID +
	                ", orderDate=" + orderDate +
	                ", price=" + price +
	                ", status='" + status + '\'' +
	                '}';
	    }
	}
