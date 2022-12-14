package com.firstbank.api.model;


import java.util.Date;

public class ClaimInputModel {
    /** 序號 */
    private int seqno;
    private int recvBranch;
    private String processMode;
    private Date dealingDate;

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public int getRecvBranch() {
        return recvBranch;
    }

    public void setRecvBranch(int recvBranch) {
        this.recvBranch = recvBranch;
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public Date getDealingDate() {
        return dealingDate;
    }

    public void setDealingDate(Date dealingDate) {
        this.dealingDate = dealingDate;
    }
}
