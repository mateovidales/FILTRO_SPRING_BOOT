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
public class StudentReq {
            @Size(min = 1, max = 255, message = "Name must be between 1 and 50 characters")
            @NotBlank(message = "Name")
            @Schema(description = "Name")
            private String name;
        
            @Size(min = 1, max = 255, message = "Email must be between 1 and 50 characters")
            @NotBlank(message = "Email")
            @Schema(description = "Email")
            private String email;
        
            @NotNull(message = "Status")
            @Schema(description = "Status")
            private Boolean active;
            
        
        }
            


