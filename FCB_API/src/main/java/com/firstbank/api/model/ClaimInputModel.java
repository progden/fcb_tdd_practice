package com.firstbank.api.model;

import java.math.BigDecimal;

public class ClaimInputModel {

	private int seqNo;
	private String fxNo;
	private String updateSeq;
	private String txVersion;
	private String prePaidFlag;
	private String advDate;
	private String recvBranch;
	private String valueDate;
	private String txDate;
	private String sendBank;
	private String sendBankName1;
	private String sendBankName2;
	private String ourCustomerFlag;
	private String customerId;
	private String beneficiaryName1;
	private String beneficiaryName2;
	private String fromMainland;
	private String remittancePurpose;
	private String sendCntryNo;
	private String sendCntryName;
	private String claimCurrency;
	private String toObu;
	private String noticeNo;
	private String noticeFlag;
	private BigDecimal spotBoughRate1;
	private BigDecimal spotBoughRateLarge;
	private BigDecimal costBoughtRate;
	private BigDecimal costBoughtRateLarge;
	private BigDecimal remittanceAmt;
	private BigDecimal costFxrate;
	private BigDecimal claimFxrate;
	private BigDecimal toFxIrAmt;
	private BigDecimal claimCharge;
	private BigDecimal claimAmt;

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getFxNo() {
		return fxNo;
	}

	public void setFxNo(String fxNo) {
		this.fxNo = fxNo;
	}

	public String getUpdateSeq() {
		return updateSeq;
	}

	public void setUpdateSeq(String updateSeq) {
		this.updateSeq = updateSeq;
	}

	public String getTxVersion() {
		return txVersion;
	}

	public void setTxVersion(String txVersion) {
		this.txVersion = txVersion;
	}

	public String getPrePaidFlag() {
		return prePaidFlag;
	}

	public void setPrePaidFlag(String prePaidFlag) {
		this.prePaidFlag = prePaidFlag;
	}

	public String getAdvDate() {
		return advDate;
	}

	public void setAdvDate(String advDate) {
		this.advDate = advDate;
	}

	public String getRecvBranch() {
		return recvBranch;
	}

	public void setRecvBranch(String recvBranch) {
		this.recvBranch = recvBranch;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public String getSendBank() {
		return sendBank;
	}

	public void setSendBank(String sendBank) {
		this.sendBank = sendBank;
	}

	public String getSendBankName1() {
		return sendBankName1;
	}

	public void setSendBankName1(String sendBankName1) {
		this.sendBankName1 = sendBankName1;
	}

	public String getSendBankName2() {
		return sendBankName2;
	}

	public void setSendBankName2(String sendBankName2) {
		this.sendBankName2 = sendBankName2;
	}

	public String getOurCustomerFlag() {
		return ourCustomerFlag;
	}

	public void setOurCustomerFlag(String ourCustomerFlag) {
		this.ourCustomerFlag = ourCustomerFlag;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBeneficiaryName1() {
		return beneficiaryName1;
	}

	public void setBeneficiaryName1(String beneficiaryName1) {
		this.beneficiaryName1 = beneficiaryName1;
	}

	public String getBeneficiaryName2() {
		return beneficiaryName2;
	}

	public void setBeneficiaryName2(String beneficiaryName2) {
		this.beneficiaryName2 = beneficiaryName2;
	}

	public String getFromMainland() {
		return fromMainland;
	}

	public void setFromMainland(String fromMainland) {
		this.fromMainland = fromMainland;
	}

	public String getRemittancePurpose() {
		return remittancePurpose;
	}

	public void setRemittancePurpose(String remittancePurpose) {
		this.remittancePurpose = remittancePurpose;
	}

	public String getSendCntryNo() {
		return sendCntryNo;
	}

	public void setSendCntryNo(String sendCntryNo) {
		this.sendCntryNo = sendCntryNo;
	}

	public String getSendCntryName() {
		return sendCntryName;
	}

	public void setSendCntryName(String sendCntryName) {
		this.sendCntryName = sendCntryName;
	}

	public String getClaimCurrency() {
		return claimCurrency;
	}

	public void setClaimCurrency(String claimCurrency) {
		this.claimCurrency = claimCurrency;
	}

	public String getToObu() {
		return toObu;
	}

	public void setToObu(String toObu) {
		this.toObu = toObu;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeFlag() {
		return noticeFlag;
	}

	public void setNoticeFlag(String noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	public BigDecimal getSpotBoughRate1() {
		return spotBoughRate1;
	}

	public void setSpotBoughRate1(BigDecimal spotBoughRate1) {
		this.spotBoughRate1 = spotBoughRate1;
	}

	public BigDecimal getSpotBoughRateLarge() {
		return spotBoughRateLarge;
	}

	public void setSpotBoughRateLarge(BigDecimal spotBoughRateLarge) {
		this.spotBoughRateLarge = spotBoughRateLarge;
	}

	public BigDecimal getCostBoughtRate() {
		return costBoughtRate;
	}

	public void setCostBoughtRate(BigDecimal costBoughtRate) {
		this.costBoughtRate = costBoughtRate;
	}

	public BigDecimal getCostBoughtRateLarge() {
		return costBoughtRateLarge;
	}

	public void setCostBoughtRateLarge(BigDecimal costBoughtRateLarge) {
		this.costBoughtRateLarge = costBoughtRateLarge;
	}

	public BigDecimal getRemittanceAmt() {
		return remittanceAmt;
	}

	public void setRemittanceAmt(BigDecimal remittanceAmt) {
		this.remittanceAmt = remittanceAmt;
	}

	public BigDecimal getCostFxrate() {
		return costFxrate;
	}

	public void setCostFxrate(BigDecimal costFxrate) {
		this.costFxrate = costFxrate;
	}

	public BigDecimal getClaimFxrate() {
		return claimFxrate;
	}

	public void setClaimFxrate(BigDecimal claimFxrate) {
		this.claimFxrate = claimFxrate;
	}

	public BigDecimal getToFxIrAmt() {
		return toFxIrAmt;
	}

	public void setToFxIrAmt(BigDecimal toFxIrAmt) {
		this.toFxIrAmt = toFxIrAmt;
	}

	public BigDecimal getClaimCharge() {
		return claimCharge;
	}

	public void setClaimCharge(BigDecimal claimCharge) {
		this.claimCharge = claimCharge;
	}

	public BigDecimal getClaimAmt() {
		return claimAmt;
	}

	public void setClaimAmt(BigDecimal claimAmt) {
		this.claimAmt = claimAmt;
	}
}
