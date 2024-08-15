package com.tia102g3.orderdetails.model;

import com.tia102g3.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "order_details")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer ordDtIID;
	private Integer productID;
	private Integer quantity;
	private Integer orderID;
}
