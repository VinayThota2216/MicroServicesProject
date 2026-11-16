package com.vinay.loans.service;

import com.vinay.loans.dto.LoansDto;
import com.vinay.loans.entity.Loans;
import com.vinay.loans.exception.LoanAlreadyExistsException;
import com.vinay.loans.exception.ResourceNotFoundException;
import com.vinay.loans.mapper.LoansMapper;
import com.vinay.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService{


    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans=loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan Already Exists with this Mobile Number is:"+mobileNumber);
        }

        loansRepository.save(LoansMapper.createNewLoan(mobileNumber));


    }

    @Override
    public LoansDto getLoanByMobileNumber(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan Not Found with this Loan Id:"+mobileNumber));

        return LoansMapper.loansEntityToLoansDto(loans,new LoansDto());
    }


    @Override
    public boolean updateLoan(LoansDto loanDto) {

        Loans loans=loansRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan Not Found with this Loan Id:"+loanDto.getLoanNumber()));

        LoansMapper.loansDtoToEntity(loanDto,loans);
        loansRepository.save(loans);
        return true;


    }

    @Override
    public boolean deleteLoan(String mobileNumber) {

        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan Not Found with this Loan Id:"+mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;

    }
}
