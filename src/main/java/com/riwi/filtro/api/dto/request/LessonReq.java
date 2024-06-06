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
public class LessonReq {
    @NotBlank
    @Size(min = 1, max = 255, message = "title between 1 and 255 characters")
    @Schema(description = "title")
    private String title;
    @NotBlank
    @Size(message = "Content of lesson")
    @Schema(description = "content")
    private String content;
    @NotNull
    @Schema(description = "status")
    private Boolean active;
    @NotNull(message = "example = 1")
    private Long classId;

}
