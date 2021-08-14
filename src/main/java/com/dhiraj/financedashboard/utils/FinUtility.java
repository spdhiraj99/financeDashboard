package com.dhiraj.financedashboard.utils;

import com.dhiraj.financedashboard.models.MutualFund;
import com.dhiraj.financedashboard.models.MutualFundRequest;

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

}
