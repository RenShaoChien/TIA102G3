package com.tia102g3.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotEmpty(message="食物名稱: 請勿空白")
	private String foodName;
	@Column(name = "foodCalories")
	@NotNull(message="食物熱量: 請勿空白")
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
