package com.dhiraj.financedashboard.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="mutualfund_tbl")
public class MutualFundRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String purchaseDate;
	private String purchaseNav;
	private String exitLoad;
	private String lockPeriod;
	private String investedAmt;
	private String units;
	private long fundId;
}
