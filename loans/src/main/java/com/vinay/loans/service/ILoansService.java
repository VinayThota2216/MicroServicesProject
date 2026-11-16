package com.vinay.loans.service;

import com.vinay.loans.dto.LoansDto;
import com.vinay.loans.entity.Loans;

public interface ILoansService {
    void createLoan(String  mobileNumber);
    LoansDto getLoanByMobileNumber(String  mobileNumber);
    boolean updateLoan(LoansDto loanDto);
    boolean deleteLoan(String  mobileNumber);

}
