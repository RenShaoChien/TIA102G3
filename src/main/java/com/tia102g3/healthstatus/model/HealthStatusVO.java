package com.tia102g3.healthstatus.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "healthStatus")

public class HealthStatusVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "healthSN", updatable = false)
	private Integer healthSN;
	@Column(name = "memberID")
	private Integer memberID;
	@Column(name = "height")
	private Integer height;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "bmi")
	private Double bmi;
	@Column(name = "BMR")
	private Integer bmr;
	@Column(name = "TDEE")
	private Integer tdee;
	@Column(name = "intensity")
	private Integer intensity;
	@Column(name = "create_dt")
	@CreationTimestamp
	private Timestamp create_dt;

	public HealthStatusVO() {
		super();
	}

	public HealthStatusVO(Integer memberID, Integer height, Integer weight, Double bmi, Integer bmr, Integer tdee,
	        Integer intensity) {
		super();
		this.memberID = memberID;
		this.height = height;
		this.weight = weight;
		this.bmi = bmi;
		this.bmr = bmr;
		this.tdee = tdee;
		this.intensity = intensity;
	}

	public HealthStatusVO(Integer healthSN, Integer memberID, Integer height, Integer weight, Double bmi, Integer bmr,
	        Integer tdee, Integer intensity, Timestamp create_dt) {
		super();
		this.healthSN = healthSN;
		this.memberID = memberID;
		this.height = height;
		this.weight = weight;
		this.bmi = bmi;
		this.bmr = bmr;
		this.tdee = tdee;
		this.intensity = intensity;
		this.create_dt = create_dt;
	}
	
	

	public Integer getHealthSN() {
		return healthSN;
	}

	public void setHealthSN(Integer healthSN) {
		this.healthSN = healthSN;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Integer getBmr() {
		return bmr;
	}

	public void setBmr(Integer bmr) {
		this.bmr = bmr;
	}

	public Integer getTdee() {
		return tdee;
	}

	public void setTdee(Integer tdee) {
		this.tdee = tdee;
	}

	public Integer getIntensity() {
		return intensity;
	}

	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public Timestamp getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Timestamp create_dt) {
		this.create_dt = create_dt;
	}

	@Override
	public String toString() {
		return "HealthStatusVO [healthSN=" + healthSN + ", memberID=" + memberID + ", height=" + height + ", weight="
		        + weight + ", bmi=" + bmi + ", bmr=" + bmr + ", tdee=" + tdee + ", intensity=" + intensity
		        + ", create_dt=" + create_dt + "]";
	}

	
}
