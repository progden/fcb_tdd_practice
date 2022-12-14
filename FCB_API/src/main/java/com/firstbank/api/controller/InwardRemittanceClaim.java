package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ClaimOutputModel rs = new ClaimOutputModel();
        //validate
        int num = model.getSeqno();
        if( num < 1000000 || num > 9999999){
            rs.setErrorCode("error-001");
            return rs;
        }
        int recvBranch = model.getRecvBranch();
        if(recvBranch < 100 || recvBranch > 999)
        {
            rs.setErrorCode("error-006");
            return rs;
        }
        //生成
        //保存
        return rs;
    }
}
