package com.riwi.filtro.infraestructure.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.StudentReq;
import com.riwi.filtro.api.dto.response.StudentResp;
import com.riwi.filtro.domain.entities.StudentEntity;
import com.riwi.filtro.domain.repositories.StudentRepository;
import com.riwi.filtro.infraestructure.abstract_services.IStudentService;
import com.riwi.filtro.utils.enums.SortType;
import com.riwi.filtro.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {
    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public StudentResp create(StudentReq request) {
        StudentEntity student = this.requestToEntity(request);
        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public StudentResp update(StudentReq request, Long id) {
        StudentEntity student = this.find(id);
        student = this.requestToEntity(request);
        student.setId(id);

        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }
    
    @Override
    public StudentResp disable(long id) {
        StudentEntity student = this.find(id);
        student.setActive(false); // Deshabilitar el estudiante
        return this.entityToResponse(this.studentRepository.save(student));
    }
    @Override
    public Page<StudentResp> findByNameContainingAndActive(int page, int size, SortType sort, String name, Boolean active) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.studentRepository.findByNameAndActive(name, true, pagination)
                .map(this::entityToResponse);
    }

    private StudentResp entityToResponse(StudentEntity entity) {
        return StudentResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .active(entity.getActive())
                .classId(entity.getClassId())
                .build();
    }

    private StudentEntity requestToEntity(StudentReq request) {
        return StudentEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .active(request.getActive())
                .build();
    }

    private StudentEntity find(long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new BadRequestException("Not found"));

    }

    @Override
    public Page<StudentResp> getAll(int page, int size, SortType sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
