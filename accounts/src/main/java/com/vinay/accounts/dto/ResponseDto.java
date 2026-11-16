package com.vinay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(
        name = "ResponseDto",
        description = "This DTO containing the status code and message "
)
public class ResponseDto {

    @Schema(
            description = "Status Code",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status Message",
            example = "Created successfully"
    )
    private String statusMessage;
}
