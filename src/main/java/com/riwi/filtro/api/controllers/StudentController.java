package com.riwi.filtro.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro.api.dto.request.StudentReq;
import com.riwi.filtro.api.dto.response.StudentResp;
import com.riwi.filtro.infraestructure.abstract_services.IStudentService;
import com.riwi.filtro.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
@Tag(name = "Student")
public class StudentController {

    private final IStudentService student;


    @Operation(summary = "Get student by name and active", description = "Get student by name and active")
    @GetMapping
    public ResponseEntity<Page<StudentResp>>getAllAndFindNameAndActive(
    @RequestParam (defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestHeader(required = false) SortType sortType,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) Boolean active
    ) {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        Page<StudentResp> students = student.findByNameContainingAndActive(page-1, size, sortType, name, active);
        return ResponseEntity.ok(students) ;
    }
    

    @ApiResponse(responseCode = "400", description = "when id is not valid", 
    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))
    }
)
    @Operation(summary = "Get student by id", description = "Get student by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResp> get(@PathVariable Long id ) {
        return ResponseEntity.ok(this.student.get(id));
    }

    @Operation(summary = "delete student by id", description = "delete student by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.student.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update student", description = "update student by id")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResp> update(
            @RequestBody StudentReq request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.student.update(request, id));
    }

    @Operation(summary = "disable a student", description = "disable a student")
    @PatchMapping("/{id}/disable")
    public ResponseEntity<StudentResp> disableStudent(@PathVariable Long id) {
        StudentResp disabledStudent = student.disable(id);
        return ResponseEntity.ok(disabledStudent);
    }

    @Operation(summary = "create a new student", description = "create a new student")
     @PostMapping
    public ResponseEntity<StudentResp> insert(
        @Validated @RequestBody StudentReq request
    ){
        return  ResponseEntity.ok(this.student.create(request));
    }


    
}