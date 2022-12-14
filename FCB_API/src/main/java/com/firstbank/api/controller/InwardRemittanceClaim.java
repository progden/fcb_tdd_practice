package com.firstbank.api.controller;

import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import com.firstbank.api.model.QueryIrInputModel;
import com.firstbank.api.model.QueryIrOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;

@RestController
@RequestMapping("/api")
public class InwardRemittanceClaim {

    public InwardRemittanceClaim(InwardRemittance irService) {
        this.irService = irService;
    }

    @Autowired
    InwardRemittance irService;
    final static String DATE_FORMAT = "yyyyMMdd";
    @GetMapping("/claim")
    public ClaimOutputModel claim(ClaimInputModel input) {
        ClaimOutputModel rs = validateInputValues(input);
        if (rs != null) return rs;

        rs = new ClaimOutputModel();

        QueryIrOutputModel queryIrOutputModel = irService.queryIr(new QueryIrInputModel());
        if(queryIrOutputModel != null && "error-001".equals(queryIrOutputModel.getErrorCode())){
            rs.setErrorCode("error-021");
            return rs;
        }


        //生成
        //保存
        return rs;
    }

    private static ClaimOutputModel validateInputValues(ClaimInputModel input) {
        ClaimOutputModel rs = new ClaimOutputModel();
        //validate
        // 驗證"序號"長度是否為7
        if( input.getSeqno() < 1000000 || input.getSeqno() > 9999999){
            rs.setErrorCode("error-001");
            return rs;
        }

        // 驗證"受理行"長度是否為3
        if(input.getRecvBranch() < 100 || input.getRecvBranch() > 999)
        {
            rs.setErrorCode("error-006");
            return rs;
        }

        // 驗證處理狀態是否為Ａ/Ｃ/Ｄ
        if (Stream.of("A", "C", "D").noneMatch(s -> s.equals(input.getProcessMode()))){
            rs.setErrorCode("error-004");
            return rs;
        }

        // 驗證承作日是否有輸入
        if(input.getDealingDate() == null){
            rs.setErrorCode("error-007");
            return rs;
        }
        return null;
    }

}
