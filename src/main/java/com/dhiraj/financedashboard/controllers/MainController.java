package com.dhiraj.financedashboard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin
@RequestMapping("/v1/api/mutualfund")
public class MainController {

	private MutualFundService mutualFundService;

	@Autowired
	public MainController(MutualFundService mutualFundService) {
		this.mutualFundService = mutualFundService;
	}

	@GetMapping("/info")
	public List<MutualFund> info() {
		return mutualFundService.listMutualFunds();
	}
	
	@PostMapping("/add")
	public String addMf(@RequestBody MutualFundRequest mfr) {
		if(mutualFundService.addMutualFund(mfr)) {
			return "Added MF to Repo";
		}
		return "Failed, check and try again";
	}
	
	@GetMapping("/currentNav/{id}")
	public String currentNav(@PathVariable Long id) {
		return mutualFundService.currentNavForId(id);
	}

}
