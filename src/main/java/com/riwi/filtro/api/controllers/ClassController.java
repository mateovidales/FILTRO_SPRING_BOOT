package com.riwi.filtro.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro.api.dto.request.ClassReq;
import com.riwi.filtro.api.dto.response.ClassResp;
import com.riwi.filtro.infraestructure.abstract_services.IClassService;
import com.riwi.filtro.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/class")
@AllArgsConstructor
@Tag(name = "class")
public class ClassController {
    private final IClassService classs;


    @Operation(summary = "Get all for name and description", description = "Get all for name and description")
    @GetMapping
    public ResponseEntity<Page<ClassResp>>getAllAndFindNameAndDescription(
        @RequestParam (defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description
        ) {
            if (Objects.isNull(sortType)) sortType = SortType.NONE;
            Page<ClassResp> classes = classs.findByNameAndDescription(page-1, size, sortType, name, description);
            return ResponseEntity.ok(classes) ;
        }

     @ApiResponse(responseCode = "400", description = "when id is not valid", 
    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))
        }
    )
    @Operation(summary = "Get class by id", description = "Get class by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassResp> get(@PathVariable Long id ) {
        return ResponseEntity.ok(this.classs.get(id));
    }

    @Operation(summary = "delete class by id", description = "delete class by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.classs.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update class by id", description = "update class by id")
      @PutMapping(path = "/{id}")
    public ResponseEntity<ClassResp> update(
            @Validated @RequestBody ClassReq request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.classs.update(request, id));
    }

    @Operation(summary = "create a new class", description = "create a new class")
     @PostMapping
    public ResponseEntity<ClassResp> insert(
        @Validated @RequestBody ClassReq request
    ){
        return  ResponseEntity.ok(this.classs.create(request));
    }


}


