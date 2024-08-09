package com.tia102g3.foodlist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tia102g3.food.model.FoodVO;
import com.tia102g3.menu.model.MenuVO;

@Entity
@Table(name="foodlist")

public class FoodListVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="foodListSN")
	private Integer foodListSN;
	
	@OneToOne
    @JoinColumn(name = "menuNumber", referencedColumnName = "menuNumber")
	private MenuVO menuVO;
	
	@ManyToOne
    @JoinColumn(name = "foodNumber", referencedColumnName = "foodNumber")
	private FoodVO foodVO;
	
	
	@Column(name="foodWeight")
	private Integer foodWeight;
	
	
	public FoodListVO() {
		super();
	}


    public Integer getFoodListSN() {
        return foodListSN;
    }


    public void setFoodListSN(Integer foodListSN) {
        this.foodListSN = foodListSN;
    }


    public MenuVO getMenuVO() {
        return menuVO;
    }


    public void setMenuVO(MenuVO menuVO) {
        this.menuVO = menuVO;
    }


    public FoodVO getFoodVO() {
        return foodVO;
    }


    public void setFoodVO(FoodVO foodVO) {
        this.foodVO = foodVO;
    }


    public Integer getFoodWeight() {
        return foodWeight;
    }


    public void setFoodWeight(Integer foodWeight) {
        this.foodWeight = foodWeight;
    }


	
	
	
}
