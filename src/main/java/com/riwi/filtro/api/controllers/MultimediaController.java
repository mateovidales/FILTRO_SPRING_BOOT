package com.riwi.filtro.api.controllers;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro.api.dto.request.MultimediaReq;
import com.riwi.filtro.api.dto.response.MultimediaResp;
import com.riwi.filtro.infraestructure.abstract_services.IMultimediaService;
import com.riwi.filtro.utils.enums.SortType;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/multimedia")
@AllArgsConstructor
@Tag(name = "multimedia")
public class MultimediaController {
    
    private final IMultimediaService multimedia;


    @GetMapping
    public ResponseEntity<Page<MultimediaResp>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType
    ){

        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        
        return ResponseEntity.ok(this.multimedia.getAll(page -1, size, sortType));
    }

    @ApiResponse(responseCode = "400", description = "when id is not valid", 
    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))
    }
)
    @GetMapping(path = "/{id}")
    public ResponseEntity<MultimediaResp> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.multimedia.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.multimedia.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MultimediaResp> update(
        @Validated @RequestBody MultimediaReq request,
        @PathVariable Long id 
    ){
        return ResponseEntity.ok(this.multimedia.update(request, id));
    }

    @PostMapping
    public ResponseEntity<MultimediaResp> insert(
        @Validated @RequestBody MultimediaReq request
    ){
        return  ResponseEntity.ok(this.multimedia.create(request));
    }

}
