package com.vinay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AccountsDto",
        description = "This DTO containing the Bank Account details"
)
public class AccountsDto {

    @Schema(
            description = "Account Number",
            example = "123456789012"

    )
    private Long accountNumber;

    @Schema(
            description = "Account Type",
            example = "Current"
    )
    @NotBlank(message = "Account type is required")
    private String  accountType;


    @Schema(
            description = "Branch Address of the Bank"
    )
    @NotBlank(message = "Branch Address is required")
    private String  branchAddress;

}
