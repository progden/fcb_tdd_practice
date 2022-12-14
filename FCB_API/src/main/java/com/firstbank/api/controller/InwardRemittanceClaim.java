package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class InwardRemittanceClaim {

	public InwardRemittanceClaim(InwardRemittance irService) {
		this.irService = irService;
	}

	@Autowired
	InwardRemittance irService;

	@GetMapping("/claim")
	public ClaimOutputModel claim(ClaimInputModel model) {
		ClaimOutputModel output = new ClaimOutputModel();

		String errorCode = checkValue(model);
		output.setErrorCode(errorCode);

		//生成
		//保存

		return output;
	}
	private String checkValue(ClaimInputModel model){
		//validate
		String errorCodeCompile = "0000";

		// 1.驗證seqno
		int seqNo = model.getSeqNo();
		String errorCodeSeqNo = checkNumLength(seqNo, "error-001", 7);
		if(!errorCodeCompile.equals(errorCodeSeqNo)){
			return errorCodeSeqNo;
		}


		// 2.驗證recvBranch
		int recvBranch = model.getRecvBranch();
		String errorCodeRecvBranch = checkNumLength(recvBranch,"error-006",3);
		if(!errorCodeCompile.equals(errorCodeRecvBranch)){
			return errorCodeRecvBranch;
		}
		return errorCodeCompile;
	}
	public boolean checkErrorCode(String errorCode){
		if("0000".equals(errorCode)){
			return false;
		}
		return true;
	}

	private String checkNumLength(int seqNo, String errorCode, int length) {
		String result = "0000";
		if (String.valueOf(seqNo).length() != length) {
			result = errorCode;
		}
		return result;
	}
}
