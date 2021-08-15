package com.dhiraj.financedashboard.utils;

import com.dhiraj.financedashboard.models.MutualFund;
import com.dhiraj.financedashboard.models.MutualFundRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FinUtility {

	public static void mapReqToModel(MutualFund mf, MutualFundRequest mfr) {
		mf.setExitLoad(mfr.getExitLoad());
		mf.setPurchaseNav(mfr.getPurchaseNav());
		mf.setPurchaseDate(mfr.getPurchaseDate());
		mf.setName(mfr.getName());
		mf.setLockPeriod(mfr.getLockPeriod());
		mf.setInvestedAmt(mfr.getInvestedAmt());
		mf.setUnits(mfr.getUnits());
		mf.setFundId(mfr.getFundId());
	}

	public static <T> String getJson(T obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(obj);
			return json; 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
