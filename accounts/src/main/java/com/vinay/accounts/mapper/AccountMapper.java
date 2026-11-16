package com.vinay.accounts.mapper;

import com.vinay.accounts.dto.AccountsDto;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.entity.Accounts;

public class AccountMapper {

    public static AccountsDto accountEntityToAccountDto(Accounts accounts, AccountsDto accountsDto) {

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts accountsDtoToAccountEntity(AccountsDto accountsDto, Accounts accounts) {

        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }


}
