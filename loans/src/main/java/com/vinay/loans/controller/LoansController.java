package com.vinay.loans.controller;

import com.vinay.loans.constants.LoansConstants;
import com.vinay.loans.dto.ErrorResponseDto;
import com.vinay.loans.dto.LoansDto;
import com.vinay.loans.dto.ResponseDto;
import com.vinay.loans.entity.Loans;
import com.vinay.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Loans",
        description = "CRUD Operations for The Loans Section"
)
@RestController
@RequestMapping(path = "/v1/api/loans",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class LoansController {


    private final ILoansService iLoansService;



    @Operation(
            summary = "Create a new customer Loan",
            description = "Create a new Loan and along with the account and customer details"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Loan Created"
    )
    @PostMapping("/create/{mobile_number}")
    public ResponseEntity<ResponseDto> createAccount(@PathVariable("mobile_number")
                                                         @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                                         String  mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }



    @Operation(
            summary = "Update the existing customer loan",
            description = "Update the existing customer loan details with the Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Loan Updated Successfully"
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
    @PutMapping("/update")
    public ResponseEntity updateAccount(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated=iLoansService.updateLoan(loansDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200_UPDATE));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_UPD_DEL, LoansConstants.MESSAGE_UPD_DEL));
        }
    }


    @Operation(
            summary = "Delete the Existing customer loan",
            description = "Delete the Existing customer loan details with Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Loan Deleted Successfully"
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
        boolean isDeleted=iLoansService.deleteLoan(mobileNumber);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200_DELETE));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_UPD_DEL, LoansConstants.MESSAGE_UPD_DEL));
        }

    }



    @Operation(
            summary = "Fetching the Loan Details",
            description = "Fetching the Loan details with mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Loan details Fetched Successfully"
    )
    @GetMapping("/get/{mobile_number}")
    public ResponseEntity getAccount(@Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                     @PathVariable("mobile_number") String mobileNumber) {

        LoansDto loansDto=iLoansService.getLoanByMobileNumber(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);

    }
}
