package com.riwi.filtro.api.dto.request;


import com.riwi.filtro.utils.enums.TypeMult;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaReq {
    @NotBlank
    @Size(message = "url of multimedia")
    @Schema(description = "url")
    private String url;
    @NotNull
    @Size(message = "Active or inactive")
    @Schema(description = "status")
    private Boolean active;
    @NotBlank
    @Size(message = "VIDEO, AUDIO or DOCUMENT")
    @Schema(description = "Username")
    private TypeMult type;
    @NotBlank
    @Size(min = 1, max = 50, message = "Name between 1 and 50 characters")
    @Schema(description = "Username")
    private Long lessonId;
}
