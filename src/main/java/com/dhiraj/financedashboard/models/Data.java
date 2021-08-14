package com.dhiraj.financedashboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
	
	@JsonProperty("date")
	String date;
	
	@JsonProperty("nav")
	String nav;
}
