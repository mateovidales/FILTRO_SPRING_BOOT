package com.riwi.filtro.api.dto.response;

import java.time.LocalDateTime;

import com.riwi.filtro.utils.enums.TypeMult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaResp {
    @Schema(description = "multimedia ID")
    private Long id;
    @Schema(description = "Url")
    private String url;
    @Schema(description = "date create")
    private LocalDateTime createdAt;
    @Schema(description = "status")
    private Boolean active;
    @Schema(description = "Type")
    private TypeMult type;
}
