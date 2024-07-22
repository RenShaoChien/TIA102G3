package com.tia102g3.foodlist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="foodlist")

public class FoodListVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="foodListSN")
	private Integer foodListSN;
	@Column(name="menuNumber")
	private Integer menuNumber;
	@Column(name="foodNumber")
	private Integer foodNumber;
	@Column(name="foodWeight")
	private Integer foodWeight;
	
	
	public FoodListVO() {
		super();
	}
	
	public FoodListVO(Integer foodListSN, Integer menuNumber, Integer foodNumber, Integer foodWeight) {
		super();
		this.foodListSN = foodListSN;
		this.menuNumber = menuNumber;
		this.foodNumber = foodNumber;
		this.foodWeight = foodWeight;
	}

	public Integer getFoodListSN() {
		return foodListSN;
	}
	public void setFoodListSN(Integer foodListSN) {
		this.foodListSN = foodListSN;
	}
	public Integer getMenuNumber() {
		return menuNumber;
	}
	public void setMenuNumber(Integer menuNumber) {
		this.menuNumber = menuNumber;
	}
	public Integer getFoodNumber() {
		return foodNumber;
	}
	public void setFoodNumber(Integer foodNumber) {
		this.foodNumber = foodNumber;
	}
	public Integer getFoodWeight() {
		return foodWeight;
	}
	public void setFoodWeight(Integer foodWeight) {
		this.foodWeight = foodWeight;
	}
	
	@Override
	public String toString() {
		return "FoodListVO [foodListSN=" + foodListSN + ", menuNumber=" + menuNumber + ", foodNumber=" + foodNumber
		        + ", foodWeight=" + foodWeight + "]";
	}
	
	
}
