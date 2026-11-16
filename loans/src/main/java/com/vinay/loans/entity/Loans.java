package com.vinay.loans.entity;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Long totalLoan;

    private Long amountPaid;

    private Long outstandingAmount;
}
