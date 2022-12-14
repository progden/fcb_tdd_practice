package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import java.math.BigDecimal;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class InwardRemittanceClaim {

	//success
	private final String successCode = "0000";

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

	private String checkValue(ClaimInputModel model) {
		//validate

		// 1.驗證seqno
		int seqNo = model.getSeqNo();
		String errorCodeSeqNo = checkNumLength(seqNo, "error-001", 7);
		if (!successCode.equals(errorCodeSeqNo)) {
			return errorCodeSeqNo;
		}

		// 2.驗證recvBranch
		int recvBranch = model.getRecvBranch();
		String errorCodeRecvBranch = checkNumLength(recvBranch, "error-006", 3);
		if (!successCode.equals(errorCodeRecvBranch)) {
			return errorCodeRecvBranch;
		}

		// 3.驗證txVersion
		int txVersion = model.getTxVersion();
		String errorCodeTxVersion = checkNumLength(txVersion, "error-017", 2);
		if (!successCode.equals(errorCodeTxVersion)) {
			return errorCodeTxVersion;
		}

		// 4.驗證seqno為數字
		int seqNoNum = model.getSeqNo();
		String errorCodeSeqNoNum = checkIsNum(seqNoNum, "error-002");
		if (!successCode.equals(errorCodeSeqNoNum)) {
			return errorCodeSeqNoNum;
		}

		// 5.驗證CliaimAmount為數字
		BigDecimal claimAmt = model.getClaimAmt();
		String errorCodeClaimAmt = checkIsBigDecimalNum(claimAmt, "error-016");
		if (!successCode.equals(errorCodeClaimAmt)) {
			return errorCodeClaimAmt;
		}

		// 6.驗證CliaimFxRate為前4碼後5碼數字
		BigDecimal claimFxrate = model.getClaimFxrate();
		String errorCodeClaimFxrate = checkFxRateFormat(claimFxrate, "error-014");
		if (!successCode.equals(errorCodeClaimFxrate)) {
			return errorCodeClaimFxrate;
		}

		return successCode;
	}


	private String checkFxRateFormat(BigDecimal claimFxrate, String errorCode) {
		String fos = claimFxrate.toString();
		boolean isMatch = fos.matches("[0-9]{4}\\.[0-9]{5}");
		if (isMatch) {
			return successCode;
		}
		return errorCode;
	}


	public boolean checkErrorCode(String errorCode) {
		if ("0000".equals(errorCode)) {
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

	private String checkIsNum(int input, String errorCode) {
		String result = "0000";
		if (input < 0) {
			result = errorCode;
		}

		return result;
	}

	private String checkIsBigDecimalNum(BigDecimal input, String errorCode) {
		String result = "0000";
		if (input.doubleValue() < 0) {
			result = errorCode;
		}

		return result;
	}
}
