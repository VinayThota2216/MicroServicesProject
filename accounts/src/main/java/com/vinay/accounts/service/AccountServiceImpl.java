
package com.vinay.accounts.service;

import com.vinay.accounts.entity.Accounts;
import com.vinay.accounts.entity.Customer;
import com.vinay.accounts.exceptions.CustomerAlreadyExistsException;
import com.vinay.accounts.exceptions.CustomerIsNotFoundException;
import com.vinay.accounts.dto.AccountsDto;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.mapper.AccountMapper;
import com.vinay.accounts.mapper.CustomerMapper;
import com.vinay.accounts.repository.AccountsRepository;
import com.vinay.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountsService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.customerDtoToCustomerEntity(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    + customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer, customerDto.getAccountsDto()));
    }

    private Accounts createNewAccount(Customer customer, AccountsDto accountsDto) {

        Accounts newAccount = AccountMapper.accountsDtoToAccountEntity(accountsDto, new Accounts());

        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);

        return newAccount;
    }



    @Override
    public CustomerDto getCustomerByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new CustomerIsNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new CustomerIsNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.customerAccountEntityToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.accountEntityToAccountDto(accounts, new AccountsDto()));
        return customerDto;
    }




    @Override
    public boolean updateAccount(String mobileNumber, CustomerDto customerDto) {

        boolean isUpdated = false;

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerIsNotFoundException("Customer", "mobileNumber", mobileNumber));

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto != null) {

            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                    .orElseThrow(() -> new CustomerIsNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

            AccountMapper.accountsDtoToAccountEntity(accountsDto, accounts);
            accountsRepository.save(accounts);
        }

        CustomerMapper.customerDtoToCustomerEntity(customerDto, customer);
        customerRepository.save(customer);

        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new CustomerIsNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}

