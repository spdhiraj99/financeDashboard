package com.dhiraj.financedashboard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.financedashboard.models.MutualFund;
import com.dhiraj.financedashboard.models.MutualFundRequest;
import com.dhiraj.financedashboard.services.MutualFundService;
import com.dhiraj.financedashboard.utils.FinUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/v1/api/mutualfund")
public class MainController {

	private MutualFundService mutualFundService;

	@Autowired
	public MainController(MutualFundService mutualFundService) {
		this.mutualFundService = mutualFundService;
	}

	/**
	 * Get All MF list
	 * 
	 * @return
	 */
	@GetMapping("/info")
	public List<MutualFund> info() {
		return mutualFundService.listMutualFunds();
	}

	/**
	 * add MF record
	 * 
	 * @param mfr
	 * @return
	 */
	@PostMapping("/add")
	public HttpStatus addMf(@RequestBody MutualFundRequest mfr) {
		log.info("Incoming Json: {} ", FinUtility.getJson(mfr));
		if (mutualFundService.addMutualFund(mfr)) {
			return HttpStatus.OK;
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	/**
	 * delete MF Record
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public HttpStatus deleteMf(@PathVariable String id) {
		if (mutualFundService.deleteMutualFund(id)) {
			return HttpStatus.OK;
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	/**
	 * get Current NAV for a fundId
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/currentNav/{id}")
	public String currentNav(@PathVariable Long id) {
		return mutualFundService.currentNavForId(id);
	}

}
