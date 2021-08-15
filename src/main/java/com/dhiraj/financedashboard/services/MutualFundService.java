package com.dhiraj.financedashboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhiraj.financedashboard.models.Data;
import com.dhiraj.financedashboard.models.MfApiResponse;
import com.dhiraj.financedashboard.models.MutualFund;
import com.dhiraj.financedashboard.models.MutualFundRequest;
import com.dhiraj.financedashboard.repository.MFRepository;
import com.dhiraj.financedashboard.utils.FinUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MutualFundService {

	private MFRepository mfRepo;
	private ApiCallService apiCall;

	@Autowired
	public MutualFundService(MFRepository mfRepo, ApiCallService apiCall) {
		this.mfRepo = mfRepo;
		this.apiCall = apiCall;
	}

	/**
	 * list all mutual fund records
	 * 
	 * @return
	 */
	public List<MutualFund> listMutualFunds() {
		List<MutualFund> mfRecords = new ArrayList<>();
		mfRepo.findAll().forEach(mfRecords::add);
		for (MutualFund mf : mfRecords) {
			updateNav(mf);
		}
		return mfRecords;
	}

	/**
	 * delete a MF Record from repo
	 * 
	 * @param recId
	 * @return
	 */
	public boolean deleteMutualFund(String recId) {
		try {
			Optional<MutualFund> opMf = mfRepo.findById(recId);
			if (opMf.isPresent()) {
				MutualFund mf = opMf.get();
				mfRepo.delete(mf);
				log.info("MF Record deleted");
				return true;
			}
		} catch (Exception e) {
			log.error("Exception occured while trying to delete MF Record");
			return false;
		}
		log.info("Record not found");
		return false;
	}

	/**
	 * add Mutual Fund to the table
	 * 
	 * @param mfr
	 * @return
	 */
	public boolean addMutualFund(MutualFundRequest mfr) {
		MutualFund mf = new MutualFund();
		FinUtility.mapReqToModel(mf, mfr);
		Double currentNav = Double.parseDouble(currentNavForId(mf.getFundId()));
		Double purchaseNav = Double.parseDouble(purchaseNavForId(mf.getFundId(), mf.getPurchaseDate()));
		Double investedAmt = Double.parseDouble(mf.getInvestedAmt());
		Double units = investedAmt / purchaseNav;
		Double currentAmt = units * currentNav;
		mf.setCurrentAmt(currentAmt.toString());
		mf.setPurchaseNav(purchaseNav.toString());
		mf.setUnits(String.format("%.3f", units));
		try {
			mfRepo.save(mf);
		} catch (Exception e) {
			log.error("Couldn't save to mf repo");
			return false;
		}
		log.info("saved");
		return true;
	}

	/**
	 * get NAV at the purchase date for a fundId
	 * 
	 * @param id
	 * @param purchaseDate
	 * @return
	 */
	public String purchaseNavForId(Long id, String purchaseDate) {
		MfApiResponse res = apiCall.getNavFromMfApi(id);
		Data purchaseData = null;
		if (Objects.nonNull(res) && Objects.nonNull(res.getData())) {
			for (Data d : res.getData()) {
				if (StringUtils.equals(d.getDate(), purchaseDate)) {
					purchaseData = d;
					break;
				}
			}
		}
		if (Objects.nonNull(purchaseData)) {
			return purchaseData.getNav();
		}
		return "Not Found";
	}

	/**
	 * get currentNav for a fundId
	 * 
	 * @param id
	 * @return
	 */
	public String currentNavForId(Long id) {
		MfApiResponse res = apiCall.getNavFromMfApi(id);
		Data latestData = null;
		if (Objects.nonNull(res) && Objects.nonNull(res.getData())) {
			if (Objects.nonNull(res.getData()[0])) {
				latestData = res.getData()[0];
			}
		}
		if (Objects.nonNull(latestData)) {
			return latestData.getNav();
		}
		return "Not Found";
	}

	/**
	 * update NAV in record to current NAV if outdated
	 * 
	 * @param mf
	 * @return
	 */
	protected boolean updateNav(MutualFund mf) {
		try {
			Double currentNav = Double.parseDouble(currentNavForId(mf.getFundId()));
			Double units = Double.parseDouble(mf.getUnits());
			Double currentAmt = units * currentNav;
			if (!StringUtils.equals(mf.getCurrentAmt(), currentAmt.toString())) {
				log.info("Updating CurrentAmount in repo");
				mf.setCurrentAmt(currentAmt.toString());
				mfRepo.save(mf);
			}
		} catch (Exception e) {
			log.error("Error Occured while updating repo");
			return false;
		}
		return true;
	}

}
