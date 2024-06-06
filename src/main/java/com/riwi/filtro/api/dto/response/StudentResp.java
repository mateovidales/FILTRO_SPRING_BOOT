package com.riwi.filtro.api.dto.response;

import com.riwi.filtro.domain.entities.ClassEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResp {
    @Schema(description = "Student ID" )
    private Long id;
    @Schema(description = "Name")
    private String name;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "status")
    private Boolean active;
    @Schema(description = "Class")
    private ClassEntity classId;
}
