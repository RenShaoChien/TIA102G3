package com.tia102g3.resmap.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tia102g3.restifo.model.RestIfoVO;

@Entity
@Table(name = "res_map")
public class ResMapVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer mapID;
	private Double mapLat;
	private Double mapLng;
	private Set<RestIfoVO> restIfos = new HashSet<RestIfoVO>();

	public ResMapVO() {
	}

	@Id
	@Column(name = "mapID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue的generator屬性指定要用哪個generator
														// //【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE,
														// TABLE】
	public Integer getMapID() {
		return this.mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	@Column(name = "mapLat")
	public Double getMapLat() {
		return this.mapLat;
	}

	public void setMapLat(Double mapLat) {
		this.mapLat = mapLat;
	}

	@Column(name = "mapLng")
	public Double getMapLng() {
		return this.mapLng;
	}

	public void setMapLng(Double mapLng) {
		this.mapLng = mapLng;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "deptVO")
	@OrderBy("restLoc asc")
	// 註1:【現在是設定成 cascade="all" lazy="false" inverse="true"之意】
	// 註2:【mappedBy="多方的關聯屬性名"：用在雙向關聯中，把關係的控制權反轉】【deptVO是EmpVO的屬性】
	// 註3:【原預設為@OneToMany(fetch=FetchType.LAZY, mappedBy="deptVO")之意】--> 【是指原為
	// lazy="true" inverse="true"之意】
	// FetchType.EAGER : Defines that data must be eagerly fetched
	// FetchType.LAZY : Defines that data can be lazily fetched
	public Set<RestIfoVO> getRestIfos() {
		return this.restIfos;
	}

	public void setRestIfos(Set<RestIfoVO> restIfos) {
		this.restIfos = restIfos;
	}

}

