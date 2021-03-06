package com.dhiraj.financedashboard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.financedashboard.models.MutualFund;
import com.dhiraj.financedashboard.models.MutualFundExtended;
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
	 * get consolidated detailed list
	 * 
	 * @return
	 */
	@GetMapping("/details")
	public List<MutualFundExtended> details() {
		return mutualFundService.detailedMfList();
	}

	/**
	 * add MF record
	 * 
	 * @param mfr
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity addMf(@RequestBody MutualFundRequest mfr) {
		log.info("Incoming Json: {} ", FinUtility.getJson(mfr));
		if (mutualFundService.addMutualFund(mfr)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * delete MF Record
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ResponseEntity deleteMf(@PathVariable String id) {
		if (mutualFundService.deleteMutualFund(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		log.info("returning error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
