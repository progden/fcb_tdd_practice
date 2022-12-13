package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import com.firstbank.api.model.QueryIrInputModel;
import com.firstbank.api.model.QueryIrOutputModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InwardRemittance {

	@GetMapping("/ir")
	public QueryIrOutputModel queryIr(QueryIrInputModel model) {


		return null;
	}
}
