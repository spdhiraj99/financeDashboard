package com.dhiraj.financedashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dhiraj.financedashboard.models.MfApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiCallService {

	private RestTemplate restTemplate;
	
	@Autowired
	public ApiCallService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public MfApiResponse getNavFromMfApi(Long id) {
		MfApiResponse res = null;
		String url = "https://api.mfapi.in/mf/" ;
		try {
			log.info("Sending Request to api.mfapi.in");
			res = restTemplate.getForObject(url, MfApiResponse.class);
			log.info("Response Received from api.mfapi.in");
		}catch(RestClientException e) {
			log.error("RestClientException occured");
		}
		return res;
	}
	
}
