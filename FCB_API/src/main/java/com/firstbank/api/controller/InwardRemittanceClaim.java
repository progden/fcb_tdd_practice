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
        ClaimOutputModel rs = new ClaimOutputModel();
        //validate
        int num = model.getSeqno();
        if(num > 2){
            rs.setErrorCode("error-001");
        }
        //生成
        //保存
        return rs;
    }
}
