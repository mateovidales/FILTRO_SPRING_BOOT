package com.riwi.filtro.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.riwi.filtro.api.dto.request.ClassReq;
import com.riwi.filtro.api.dto.response.ClassResp;
import com.riwi.filtro.domain.entities.ClassEntity;
import com.riwi.filtro.domain.repositories.ClassRepository;
import com.riwi.filtro.infraestructure.abstract_services.IClassService;
import com.riwi.filtro.utils.enums.SortType;
import com.riwi.filtro.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClassService implements IClassService{

    @Autowired
    private final ClassRepository classRepository;

    @Override
    public ClassResp create(ClassReq request) {
        ClassEntity classs = this.requestToEntity(request);
        return this.entityToResponse(this.classRepository.save(classs)); 
    }

    @Override
    public ClassResp get(Long id) {
       return this.entityToResponse(this.find(id));
    }

    @Override
    public ClassResp update(ClassReq request, Long id) {
        ClassEntity classs = this.find(id);
        classs = this.requestToEntity(request);
        classs.setId(id);

        return this.entityToResponse(this.classRepository.save(classs));
    }

    @Override
    public void delete(Long id) {
        this.classRepository.save(this.find(id));
    }
    
    @Override
    public Page<ClassResp> findByNameAndDescription(int page, int size, SortType sort, String name, String description) {
        if (page < 0)
            page = 0;
    
        PageRequest pagination = null;
    
        switch (sort) {
            case NONE:
                pagination = PageRequest.of(page, size);
                break;
            case ASC:
                pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
                break;
            case DESC:
                pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
                break;
        }
    
        return this.classRepository.findByNameAndDescription(name, description, pagination);

    }

private ClassResp entityToResponse(ClassEntity entity){
        return ClassResp.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .createdAt(entity.getCreatedAt())
        .active(entity.getActive())
        .build();
    }

    private ClassEntity requestToEntity (ClassReq request){
        return ClassEntity.builder()
        .name(request.getName())
        .description(request.getDescription())
        .active(request.getActive())
        .build();
    }

    private ClassEntity find(long id){
    return this.classRepository.findById(id).orElseThrow(()-> new BadRequestException("Not found"));
    
}

    @Override
    public Page<ClassResp> getAll(int page, int size, SortType sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    
 }
    
