package com.vinay.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Schema(
        name = "ErrorResponseDto",
        description = "This DTO containing the error response details"
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

