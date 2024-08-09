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

@Entity
@Table(name = "menu")
public class MenuVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menuNumber", updatable = false)
	private Integer menuNumber;
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
		return "MenuVO [menuNumber=" + menuNumber + ", imageNumber=" + imageNumber + ", menuImage="
		        + Arrays.toString(menuImage) + "]";
	}
	

}
