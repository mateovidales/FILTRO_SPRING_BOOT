package com.riwi.filtro.api.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.riwi.filtro.domain.entities.MultimediaEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResp {
    @Schema(description = "Lesson ID")
    private Long id;
    @Schema(description = "Title")
    private String title;
    @Schema(description = "Content")
    private String content;
    @Schema(description = "Create ")
    private LocalDate createdAt;
    @Schema(description = "status")
    private Boolean active;
    @Schema(description = "Multimedia")
     private List<MultimediaEntity> multimedia;
}
