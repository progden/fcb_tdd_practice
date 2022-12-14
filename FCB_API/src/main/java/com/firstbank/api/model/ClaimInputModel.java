package com.firstbank.api.model;



public class ClaimInputModel {
    private int seqno;
    private int recvBranch;
    private String processMode;

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


}
