package com.vinay.loans.mapper;

import com.vinay.loans.constants.LoansConstants;
import com.vinay.loans.dto.LoansDto;
import com.vinay.loans.entity.Loans;

import java.util.Random;

public class LoansMapper {

    public static Loans loansDtoToEntity(LoansDto loansDto, Loans loans) {

        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setTotalLoan(loansDto.getTotalLoan());

        return loans;
    }

    public static LoansDto loansEntityToLoansDto(Loans loans,LoansDto loansDto) {


        loansDto.setLoanId(loans.getLoanId());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setTotalLoan(loans.getTotalLoan());

        return loansDto;
    }

    public static Loans createNewLoan(String mobileNumber){
        Loans loans=new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setOutstandingAmount(LoansConstants.LOAN_LIMIT);
        loans.setTotalLoan(LoansConstants.LOAN_LIMIT);
        loans.setAmountPaid(0L);
        loans.setLoanType(LoansConstants.PERSONAL_LOAN);
        return loans;
    }


}
