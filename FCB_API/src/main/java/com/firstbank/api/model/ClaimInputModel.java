package com.firstbank.api.model;

import lombok.Data;

@Data
public class ClaimInputModel {
    private int seqno;
    private int recvBranch;
    private String processMode;

}
