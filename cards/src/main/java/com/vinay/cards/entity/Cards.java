package com.vinay.cards.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Data
@Table(name = "cards")

public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Long totalLimit;

    @Column(name = "amount_used")
    private Long amountUsed;

    @Column(name = "available_amount")
    private Long availableAmount;
}
