package com.tia102g3.food.model;

import javax.persistence.*;

@Entity
@Table(name = "food")

public class FoodVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "foodNumber", updatable = false)
	private Integer foodNumber;
	@Column(name ="foodTypeNumber")
	private Integer foodTypeNumber;
	@Column(name = "foodName")
	private String foodName;
	@Column(name = "foodCalories")
	private Integer foodCalories;
	
	
	public FoodVO() {
		super();
	}
	
	public FoodVO(Integer foodNumber, Integer foodTypeNumber, String foodName, Integer foodCalories) {
		super();
		this.foodNumber = foodNumber;
		this.foodTypeNumber = foodTypeNumber;
		this.foodName = foodName;
		this.foodCalories = foodCalories;
	}

	public Integer getFoodNumber() {
		return foodNumber;
	}
	public void setFoodNumber(Integer foodNumber) {
		this.foodNumber = foodNumber;
	}
	public Integer getFoodTypeNumber() {
		return foodTypeNumber;
	}
	public void setFoodTypeNumber(Integer foodTypeNumber) {
		this.foodTypeNumber = foodTypeNumber;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Integer getFoodCalories() {
		return foodCalories;
	}
	public void setFoodCalories(Integer foodCalories) {
		this.foodCalories = foodCalories;
	}

	@Override
	public String toString() {
		return "FoodVO [foodNumber=" + foodNumber + ", foodTypeNumber=" + foodTypeNumber + ", foodName=" + foodName
		        + ", foodCalories=" + foodCalories + "]";
	}
	
	
}
