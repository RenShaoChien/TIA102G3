package com.tia102g3.likefood.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g3.food.model.FoodVO;

@Entity
@Table(name = "likefood")
public class LikeFoodVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likeFoodSN", updatable = false)	
    private Integer likeFoodSN;
	
	@Column(name = "memberID")
	private Integer memberID;
	
	@ManyToOne
    @JoinColumn(name = "foodNumber", referencedColumnName = "foodNumber")
    private FoodVO foodVO;
	
	@Column(name = "foodPreference")
	private Boolean foodPreference;
	
	public LikeFoodVO() {
		super();
	}

    public Integer getLikeFoodSN() {
        return likeFoodSN;
    }

    public void setLikeFoodSN(Integer likeFoodSN) {
        this.likeFoodSN = likeFoodSN;
    }

    public Integer getMemberID() {
        return memberID;
    }

    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }

    public FoodVO getFoodVO() {
        return foodVO;
    }

    public void setFoodVO(FoodVO foodVO) {
        this.foodVO = foodVO;
    }

    public Boolean getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(Boolean foodPreference) {
        this.foodPreference = foodPreference;
    }

    @Override
    public String toString() {
        return "LikeFoodVO [likeFoodSN=" + likeFoodSN + ", memberID=" + memberID + ", foodVO=" + foodVO
                + ", foodPreference=" + foodPreference + "]";
    }
    
    

}
