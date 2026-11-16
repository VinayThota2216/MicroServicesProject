package com.vinay.cards.dto;

import com.vinay.cards.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "CardsDto",
        description = "This DTO containing the Card Details"
)
public class CardsDto extends SuperEntity {

    private Long cardId;

    @Schema(
            description = "Mobile Number",
            example = "9133755037"

    )
    @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
    private String mobileNumber;


    @Schema(
            description = "Card Number",
            example = "123456789012"

    )
    @Pattern(regexp = "^[0-9]{12}$" , message = "Invalid Card Number")
    private String cardNumber;


    @Schema(
            description = "Card Type",
            example = "CREDIT CARD"

    )
    @NotEmpty(message = "Card type shouldn't be empty or null")
    private String cardType;


    @Schema(
            description = "Total Limit Amount ",
            example = "100000000L"

    )
    @Positive(message = "Total amount should be Greater the Zero")
    private Long totalLimit;



    @Schema(
            description = "Used amount"
    )
    @PositiveOrZero(message = "Used amount should be greater than or equal to zero")
    private Long amountUsed;


    @Schema(
            description = "Available Amount"

    )
    @PositiveOrZero(message = "Available amount should be greater than or equal to zero")
    private Long availableAmount;
}
