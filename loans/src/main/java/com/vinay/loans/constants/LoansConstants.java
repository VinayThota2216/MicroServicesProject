package com.vinay.loans.constants;

import lombok.Data;

public class LoansConstants {

    private LoansConstants() {
    }



    public static final String  PERSONAL_LOAN = "Personal Loan";
    public static final Long  LOAN_LIMIT = 100000L;
    public static final String STATUS_201="201";
    public static final String MESSAGE_201="Loan created successfully";
    public static final String STATUS_200="200";
    public static final String MESSAGE_200="Request processed successfully";
    public static final String MESSAGE_200_UPDATE="Loan updated successfully";
    public static final String MESSAGE_200_DELETE="Loan deleted successfully";
    public static final String STATUS_UPD_DEL = "417";
    public static final String MESSAGE_UPD_DEL = "Loan not Updated and deleted ";

}
