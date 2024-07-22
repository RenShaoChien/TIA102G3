package com.tia102g3.likefood.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likefood")
public class LikeFoodVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberID", updatable = false)
	private Integer memberID;
	@Column(name = "foodNumber")
	private Integer foodNumber;
	@Column(name = "foodPreference")
	private Boolean foodPreference;
	
	public LikeFoodVO() {
		super();
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getFoodNumber() {
		return foodNumber;
	}

	public void setFoodNumber(Integer foodNumber) {
		this.foodNumber = foodNumber;
	}

	public Boolean getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(Boolean foodPreference) {
		this.foodPreference = foodPreference;
	}

	@Override
	public String toString() {
		return "FoodLikeVO [memberID=" + memberID + ", foodNumber=" + foodNumber + ", foodPreference=" + foodPreference
		        + "]";
	}
	
}
