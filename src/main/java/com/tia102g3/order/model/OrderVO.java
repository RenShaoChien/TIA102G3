package com.tia102g3.order.model;

import com.tia102g3.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "orderid")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderVO implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
	@NonNull
    private Integer orderID;
	private java.sql.Timestamp orderDate ;
	private String status;
	private Integer totalPrice;
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID", nullable = false)
	private Member member;

}
