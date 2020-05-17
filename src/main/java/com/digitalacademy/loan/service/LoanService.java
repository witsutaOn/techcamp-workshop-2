package com.digitalacademy.loan.service;

import com.digitalacademy.loan.constants.LoanError;
import com.digitalacademy.loan.exception.LoanException;
import com.digitalacademy.loan.model.LoanInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoanService {
    private static final Logger log = LogManager.getLogger(LoanService.class.getName());

    public LoanInfo getLoanInfoById(Long id) throws Exception{
        log.info("Get loan by customer id" + id);

        LoanInfo loneInfo = new LoanInfo();

        if(id.equals(1L)){
            loneInfo.setId(1L);
            loneInfo.setStatus("OK");
            loneInfo.setAccountPayable("102-222-222");
            loneInfo.setAccountReceivable("10-232-445-78");
            loneInfo.setPrincipalAmount(3000.00);
        }
        else if(id.equals(2L)){
            log.info("id: {}", id);
            throw new LoanException(
                    LoanError.GET_LOAN_INFO_NOT_FOUND,
                    HttpStatus.BAD_REQUEST
            );
        }
        else{
            log.info(id.toString());
            throw new Exception("Test throw new exception");
        }

        return loneInfo;
    }
}