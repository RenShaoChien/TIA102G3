package com.tia102g3.restmap.model;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tia102g3.restifo.model.RestIfoVO;

@Entity
@Table(name = "rest_map") // 代表這個class是對應到資料庫的實體table，目前對應的table是rest_map
public class RestMapVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "mapID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue的generator屬性指定要用哪個generator
                                                        // 【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE, TABLE】
    private Integer mapID;

    @Column(name = "mapLat")
    private Double mapLat;

    @Column(name = "mapLng")
    private Double mapLng;

    // @OneToMany (雙向一對多/多對一) 的一對多
    // 註1:【現在是設定成 cascade="all" lazy="false" inverse="true"之意】
    // 註2:【mappedBy="多方的關聯屬性名"：用在雙向關聯中，把關係的控制權反轉】【restMapVO是RestIfoVO的屬性】
    // 註3:【原預設為@OneToMany(fetch=FetchType.LAZY, mappedBy="restMapVO")之意】--> 【是指原為 lazy="true" inverse="true"之意】
    // FetchType.EAGER : Defines that data must be eagerly fetched
    // FetchType.LAZY : Defines that data can be lazily fetched
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "restMapVO")
    @OrderBy("restLoc asc")
    @JsonManagedReference // 管理反向關係，防止循環引用
    private Set<RestIfoVO> restIfos = new HashSet<RestIfoVO>();

    public RestMapVO() {
    }

    public Integer getMapID() {
        return mapID;
    }

    public void setMapID(Integer mapID) {
        this.mapID = mapID;
    }

    public Double getMapLat() {
        return mapLat;
    }

    public void setMapLat(Double mapLat) {
        this.mapLat = mapLat;
    }

    public Double getMapLng() {
        return mapLng;
    }

    public void setMapLng(Double mapLng) {
        this.mapLng = mapLng;
    }

    public Set<RestIfoVO> getRestIfos() {
        return restIfos;
    }

    public void setRestIfos(Set<RestIfoVO> restIfos) {
        this.restIfos = restIfos;
    }
}
