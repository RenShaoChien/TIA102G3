package com.tia102g3.order.model;

import com.tia102g3.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


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
	private Member member;
	private Date orderDate ;
	private String status;
	private Integer totalPrice;
}
