package com.vinay.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ResponseDto",
        description = "This DTO containing the Response Details"
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
