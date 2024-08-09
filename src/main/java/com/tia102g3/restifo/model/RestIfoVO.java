//package com.tia102g3.restifo.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
////import org.hibernate.validator.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//import com.tia102g3.resmap.model.ResMapVO;
//
//@Entity // 要加上@Entity才能成為JPA的一個Entity類別
//@Table(name = "rest_lfo") // 代表這個class是對應到資料庫的實體table，目前對應的table是EMP2
//public class RestIfoVO implements java.io.Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private Integer restLoc;
//	private ResMapVO resMapVO;
//	private String restName;
//	private String restAddress;
//	private String restTime;
//	private String restTel;
//
//	public RestIfoVO() { // 必需有一個不傳參數建構子(JavaBean基本知識)
//	}
//
//	@Id // @Id代表這個屬性是這個Entity的唯一識別屬性，並且對映到Table的主鍵
//	@Column(name = "restLoc") // @Column指這個屬性是對應到資料庫Table的哪一個欄位 //【非必要，但當欄位名稱與屬性名稱不同時則一定要用】
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue的generator屬性指定要用哪個generator
//														// //【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE,
//														// TABLE】
//	public Integer getRestLoc() {
//		return this.restLoc;
//	}
//
//	public void setRestLoc(Integer restLoc) {
//		this.restLoc = restLoc;
//	}
//
//	// @ManyToOne (雙向多對一/一對多) 的多對一
//	// 【此處預設為 @ManyToOne(fetch=FetchType.EAGER) --> 是指 lazy="false"之意】【注意:
//	// 此處的預設值與XML版 (p.127及p.132) 的預設值相反】
//	// 【如果修改為 @ManyToOne(fetch=FetchType.LAZY) --> 則指 lazy="true" 之意】
//	@ManyToOne
//	@JoinColumn(name = "mapID") // 指定用來join table的column
//	public ResMapVO getResMapVO() {
//		return this.resMapVO;
//	}
//
//	public void settResMapVO(ResMapVO resMapVO) {
//		this.resMapVO = resMapVO;
//	}
//
//	@Column(name = "restName")
//	@NotEmpty(message = "餐廳名稱: 請勿空白")
//	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$", message = "餐廳名稱: 只能是中、英文字母、數字和_ ")
//	public String getRestName() {
//		return this.restName;
//	}
//
//	public void setRestName(String restName) {
//		this.restName = restName;
//	}
//
//	@Column(name = "restAddress")
//	@NotEmpty(message = "餐廳地址: 請勿空白")
//	@Size(min = 1, max = 100, message = "餐廳地址: 長度必需在{min}到{max}之間")
//	public String getRestAddress() {
//		return this.restAddress;
//	}
//
//	public void setrestAddress(String restAddress) {
//		this.restAddress = restAddress;
//	}
//
//	@Column(name = "restTime")
//	@NotNull(message = "營業時間: 請勿空白")
//	@Size(min = 1, max = 150, message = "營業時間: 長度必需在{min}到{max}之間")
//	public String getRestTime() {
//		return this.restTime;
//	}
//
//	public void setRestTime(String restTime) {
//		this.restTime = restTime;
//	}
//
//	@Column(name = "restTel")
//	@Pattern(regexp = "^((\\d{2,3}-?|\\(\\d{2,3}\\))\\d{3,4}-?\\d{4}|09\\d{2}(\\d{6}|-\\d{3}-\\d{3}))$", message = "餐廳電話: 電話格式有誤")
//	public String getRestTel() {
//		return this.restTel;
//	}
//
//	public void setRestTel(String restTel) {
//		this.restTel = restTel;
//	}
//
//}
//
