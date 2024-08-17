package com.tia102g3.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g3.member.model.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "orderid")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer orderID;
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
//    @ToString.Exclude // 避免無限循環
//    @EqualsAndHashCode.Exclude // 避免無限循環
	private Member member;
	private Timestamp orderDate ;
	private String status;
	private Integer totalPrice;
}
