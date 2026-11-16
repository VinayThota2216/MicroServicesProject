package com.vinay.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoansDto {

    private Long loanId;

    @Schema(
            description = "Mobile Number",
            example = "9133755037"

    )
    @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
    private String mobileNumber;

    @Schema(
            description = "Loan Number",
            example = "123456789123"

    )
    @Pattern(regexp = "^[0-9]{12}$", message = "Invalid Card Number")
    private String loanNumber;

    @Schema(
            description = "Loan Type",
            example = "PERSONAL LOAN"

    )
    @NotEmpty(message = "Loan type should not be null or empty")
    private String loanType;

    @Schema(
            description = "Total Loan Amount"

    )
    @Positive(message = "Total loan amount should be greater than zero ")
    private Long totalLoan;


    @Schema(
            description = "Paid amount"

    )
    @PositiveOrZero(message = "Paid amount should be greater than or equals to Zero")
    private Long amountPaid;

    @Schema(
            description = "Outstanding Amount"

    )
    @PositiveOrZero(message = "Outstanding amount should be greater than or equals to Zero")
    private Long outstandingAmount;
}
