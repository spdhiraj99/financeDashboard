package com.dhiraj.financedashboard.models;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class MutualFundExtended {
	private long id;
	private String name;
	private String purchaseDate;
	private String purchaseNav;
	private String exitLoad;
	private String lockPeriod;
	private String investedAmt;
	private String units;
	private long fundId;
	private Double currentNav;
	private String currentAmt;
	private Double profit;
	private Double STCG;
	private Double LTCG;
	private Double withdrawNoTax;
	private Double withdrawTax;
}
