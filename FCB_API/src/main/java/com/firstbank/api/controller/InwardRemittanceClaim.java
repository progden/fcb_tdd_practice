package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import java.util.stream.Stream;
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
    public ClaimOutputModel claim(ClaimInputModel input) {
        ClaimOutputModel rs = new ClaimOutputModel();

        //validate
        if( input.getSeqno() < 1000000 || input.getSeqno() > 9999999){
            rs.setErrorCode("error-001");
            return rs;
        }
        if(input.getRecvBranch() < 100 || input.getRecvBranch() > 999)
        {
            rs.setErrorCode("error-006");
            return rs;
        }
        if (Stream.of("A", "C", "D").noneMatch(s -> s.equals(input.getProcessMode()))){
            rs.setErrorCode("error-004");
            return rs;
        }
        //生成
        //保存
        return rs;
    }
}
