package com.vinay.accounts.controller;


import com.vinay.accounts.constants.AccountConstant;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.dto.ErrorResponseDto;
import com.vinay.accounts.dto.ResponseDto;
import com.vinay.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts",
        description = "CRUD Operations for The Accounts Section"
)
@RestController
@RequestMapping(path = "/v1/api/accounts",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @Operation(
            summary = "Create a new Customer Account",
            description = "Create a new customer and along with the account and customer details"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.ACCOUNT_CREATED, AccountConstant.ACCOUNT_CREATED_MESSAGE));
    }




    @Operation(
            summary = "Update the existing customer",
            description = "Update the existing customer and account details with the Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Updated Successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "EXPECTATION_FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update/{mobile_number}")
    public ResponseEntity updateAccount(@PathVariable("mobile_number")
                                            @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                            String mobileNumber,
                                            @Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated=accountsService.updateAccount(mobileNumber,customerDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.ACCOUNT_UPDATED, AccountConstant.ACCOUNT_UPDATED_MESSAGE));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.ACCOUNT_UPD_DEL, AccountConstant.ACCOUNT_UPD_DEL_MESSAGE));
        }
    }




    @Operation(
            summary = "Delete the Existing customer",
            description = "Delete the Existing customer and account details with Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Deleted Successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "EXPECTATION_FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete/{mobile_number}")
    public ResponseEntity deleteAccount(@PathVariable("mobile_number")
                                            @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                             String mobileNumber) {
        boolean isDeleted=accountsService.deleteAccount(mobileNumber);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.ACCOUNT_DELETED, AccountConstant.ACCOUNT_DELETED_MESSAGE));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.ACCOUNT_UPD_DEL, AccountConstant.ACCOUNT_UPD_DEL_MESSAGE));
        }

    }


    @Operation(
            summary = "Fetching the Customer and Accounts Details",
            description = "Fetching the customer and accounts details with mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account Fetched Successfully"
    )
    @GetMapping("/get/{mobile_number}")
    public ResponseEntity getAccount(@Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                         @PathVariable("mobile_number") String mobileNumber) {

        CustomerDto customerDto=accountsService.getCustomerByMobileNumber(mobileNumber);

        return ResponseEntity.ok(customerDto);

    }
}
