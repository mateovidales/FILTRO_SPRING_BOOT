package com.riwi.filtro.api.dto.request;

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
public class ClassReq {
    @NotBlank
    @Size(min = 1, max = 255, message = "Name between 1 and 255 characters")
    @Schema(description = "name")
    private String name;
    @NotBlank
    @Size(message = "Description")
    @Schema(description = "description")
    private String description;
    @NotNull
    @Schema(description = "status")
    private Boolean active;
    
}
