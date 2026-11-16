package com.vinay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(
        name = "CustomerDto",
        description = "This DTO containing the Customer Details"
)
public class CustomerDto {


    @Schema(
            description = "Customer Name",
            example = "Vinay"
    )
    @NotBlank(message = "Customer name shouldn't be empty or blank or null")
    @Size(min = 4,max = 20 , message = "Customer name  must be contain 4-20 characters")
    private String name;

    @Schema(
            description = "Customer Mobile Number",
            example = "9133755037"
    )
    @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
    private String mobileNumber;


    @Schema(
            description = "Customer Email",
            example = "vinaythota543@gmail.com"
    )
    @NotBlank(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9+._-]+@[A-Za-z0-9._-]+\\.(com)$", message = "Invalid Email")
    private String email;


    @Schema(
            description = "AccountsDTO details"
    )

    @Valid
    private AccountsDto accountsDto;
}
