package com.dhiraj.financedashboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MfApiResponse {
	
	@JsonProperty("meta")
	Meta meta;
	
	@JsonProperty("data")
	Data[] data;
	
	@JsonProperty("status")
	String status;
	
}
