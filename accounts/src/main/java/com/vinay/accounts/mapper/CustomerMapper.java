package com.vinay.accounts.mapper;

import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.entity.Accounts;
import com.vinay.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto customerAccountEntityToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer customerDtoToCustomerEntity(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }


}
