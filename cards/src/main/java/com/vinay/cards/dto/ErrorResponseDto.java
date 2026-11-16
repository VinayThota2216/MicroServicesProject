package com.vinay.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ErrorResponseDto",
        description = "This DTO containing the Error Response Details"
)
public class ErrorResponseDto {


    @Schema(description = "api Path")
    private String apiPath;

    @Schema(description = "status")
    private HttpStatus status;

    @Schema(description = "message")
    private String message;

    @Schema(description = "time")
    private LocalDateTime timestamp;
}
