package com.tia102g3.menu.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
public class MenuVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menuNumber", updatable = false)
	@NotNull(message="餐點編號: 請勿空白")
	private Integer menuNumber;
	
	@Column(name = "menuName")
	private String menuName;
	
	@Column(name = "imageNumber")
	private Integer imageNumber;
	@Lob
    @Column(name = "menuImage", columnDefinition = "LONGBLOB")
	private byte[] menuImage;
	
	public MenuVO() {
		super();
	}

    public Integer getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(Integer menuNumber) {
        this.menuNumber = menuNumber;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(Integer imageNumber) {
        this.imageNumber = imageNumber;
    }

    public byte[] getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(byte[] menuImage) {
        this.menuImage = menuImage;
    }

    @Override
    public String toString() {
        return "MenuVO [menuNumber=" + menuNumber + ", menuName=" + menuName + ", imageNumber=" + imageNumber
                + ", menuImage=" + Arrays.toString(menuImage) + "]";
    }


}
