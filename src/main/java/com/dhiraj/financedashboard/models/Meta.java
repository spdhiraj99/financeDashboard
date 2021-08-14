package com.dhiraj.financedashboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
	
	@JsonProperty("fund_house")
	String fundHouse;
	
	@JsonProperty("scheme_type")
	String schemeType;
	
	@JsonProperty("scheme_category")
	String schemeCategory;
	
	@JsonProperty("scehme_code")
	String schemeCode;
	
	@JsonProperty("scheme_name")
	String schemeName;
	
}
