package com.vinay.cards.controller;

import com.vinay.cards.constants.CardsConstants;
import com.vinay.cards.dto.CardsDto;
import com.vinay.cards.dto.ErrorResponseDto;
import com.vinay.cards.dto.ResponseDto;
import com.vinay.cards.entity.Cards;
import com.vinay.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Cards",
        description = "CRUD Operations for The Cards Section"
)
@RestController
@RequestMapping(path = "/v1/api/cards",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final ICardsService iCardsService;


    @Operation(
            summary = "Create a new Customer Card",
            description = "Create a new Card and along with the Account and Customer details"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Card Created"
    )
    @PostMapping("/createCard/{mobile_number}")
    public ResponseEntity<ResponseDto> createCard(@PathVariable("mobile_number")
                                                      @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                                      String  mobileNumber){
        iCardsService.createCards(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }




    @Operation(
            summary = "Fetching the Card Details",
            description = "Fetching the card details with mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Card details Fetched Successfully"
    )
    @GetMapping("/getCard/{mobile_number}")
    public ResponseEntity getCard(@PathVariable("mobile_number")
                                                   @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                                   String  mobileNumber){
        CardsDto cardsDto=iCardsService.getCardsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }



    @Operation(
            summary = "Update the existing customer card",
            description = "Update the existing customer card details with the Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card Updated Successfully"
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
    @PutMapping("/updateCard")
    public ResponseEntity updateCard(@Valid @RequestBody CardsDto cardsDto){
        boolean isUpdated=iCardsService.updateCards(cardsDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_UPD_DEL,CardsConstants.MESSAGE_UPD_DEL));
        }
    }



    @Operation(
            summary = "Delete the Existing customer card",
            description = "Delete the Existing customer card details with Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card Deleted Successfully"
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
    @DeleteMapping("/deleteCard/{mobile_number}")
    public ResponseEntity deleteCard(@PathVariable("mobile_number")
                                         @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                         String  mobileNumber){
        boolean isDeleted=iCardsService.deleteCards(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_UPD_DEL,CardsConstants.MESSAGE_UPD_DEL));
        }
    }



}
