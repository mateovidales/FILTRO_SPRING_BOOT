package com.riwi.filtro.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.riwi.filtro.api.dto.request.LessonReq;
import com.riwi.filtro.api.dto.response.LessonResp;
import com.riwi.filtro.infraestructure.abstract_services.ILessonService;
import com.riwi.filtro.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lesson")
@AllArgsConstructor
@Tag(name = "lesson")
public class LessonController {
    
      private final ILessonService lesson;


      @Operation(summary = "Get all lessons", description = "Get all lessons")
      @GetMapping
      public ResponseEntity<Page<LessonResp>> getAll(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "5") int size,
          @RequestHeader(required = false) SortType sortType
      ){
  
          if (Objects.isNull(sortType)) sortType = SortType.NONE;
          
          return ResponseEntity.ok(this.lesson.getAll(page -1, size, sortType));
      }

      @ApiResponse(responseCode = "400", description = "when id is not valid", 
        content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))
        }
    )
    @Operation(summary = "get lessons by id", description = "get lessons by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResp> get(@PathVariable Long id ) {
        return ResponseEntity.ok(this.lesson.get(id));
    }

    @Operation(summary = "delete lesson by id", description = "delete lesson by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.lesson.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update lesson", description = "update lessons")
    @PutMapping("/{id}")
    public ResponseEntity<LessonResp> update(
        @RequestBody LessonReq request,
        @PathVariable Long id
    ){
        return ResponseEntity.ok(this.lesson.update(request, id));
    }
    

    @Operation(summary = "disable lesson", description = "disable lesson")
     @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
        this.lesson.disable(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "create a new lesson", description = "create a new lesson")
     @PostMapping
    public ResponseEntity<LessonResp> insert(
        @Validated @RequestBody LessonReq request
    ){
        return  ResponseEntity.ok(this.lesson.create(request));
    }


}
