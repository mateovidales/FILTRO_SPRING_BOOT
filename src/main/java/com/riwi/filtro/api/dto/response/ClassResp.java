package com.riwi.filtro.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.riwi.filtro.domain.entities.LessonEntity;
import com.riwi.filtro.domain.entities.StudentEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassResp {
    @Schema(description = "class ID")
    private Long id;
    @Schema(description = "name")
    private String name;
    @Schema(description = "description")
    private String description;
    @Schema(description = "date create")
    private LocalDateTime createdAt;
    @Schema(description = "active")
    private Boolean active;
    @Schema(description = "List students")
    private List<StudentEntity> student;
    @Schema(description = "list lessons")
    private List<LessonEntity> lesson;
}
