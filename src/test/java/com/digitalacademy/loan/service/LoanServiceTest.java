package com.digitalacademy.loan.service;


import com.digitalacademy.loan.exception.LoanException;
import com.digitalacademy.loan.model.LoanInfo;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoanServiceTest {
    @InjectMocks
    LoanService loanService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanService = new LoanService();
    }
    @DisplayName("get loan info by id equals 1 should return loan information")
    @Test
    void testLoanInfoByIdEquals1() throws Exception{
        LoanInfo resp = loanService.getLoanInfoById(1L);

        assertEquals("1", resp.getId().toString());
        assertEquals("OK", resp.getStatus());
        assertEquals("102-222-222", resp.getAccountPayable());
        assertEquals("10-232-445-78", resp.getAccountReceivable());
        assertEquals(3000.0 , resp.getPrincipalAmount(),0);
    }

    @DisplayName("Test get loan info by id equals 2 should throw loan Exception loan info not found")
    @Test
    void testGetLoanInfoByIdEquals2() throws Exception{
        Long reqParam = 2L;
        LoanException thrown = assertThrows(LoanException.class,
                () -> loanService.getLoanInfoById(reqParam),
                "Expected loanInfoById(reqParam) to throw, but it didn't");

        assertEquals(400 , thrown.getHttpStatus().value());
        assertEquals("LOAN4002", thrown.getLoanError().getCode());
        assertEquals("Loan information not found", thrown.getLoanError().getMessage());
    }

    @DisplayName("Test get loan info by id equals 3 should throw Exception : test throw new exception")
    @Test
    void testGetLoanInfoByIdEquals3() throws Exception{
        Long reqParam = 3L;
        Exception thrown = assertThrows(Exception.class,
                () -> loanService.getLoanInfoById(reqParam),
                "Expected loanInfoById(reqParam) to throw, but it didn't");
        assertEquals("Test throw new exception", thrown.getMessage());

    }
}
