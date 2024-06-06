package com.riwi.filtro.infraestructure.services;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.LessonReq;
import com.riwi.filtro.api.dto.response.LessonResp;
import com.riwi.filtro.domain.entities.LessonEntity;
import com.riwi.filtro.domain.repositories.LessonRepository;
import com.riwi.filtro.infraestructure.abstract_services.ILessonService;
import com.riwi.filtro.utils.enums.SortType;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class LessonService implements ILessonService{

    @Autowired
    private final LessonRepository lessonRepository;

    @Override
    public LessonResp create(LessonReq request) {
    LessonEntity lesson = this.requestToEntity(request);
    return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public LessonResp update(LessonReq request, Long id) {
        LessonEntity lesson = this.find(id);
        lesson = this.requestToEntity(request);
        lesson.setId(id);

        return this.entityToResponse(this.lessonRepository.save(lesson));
    }


    @Override
    public void delete(Long id) {
     this.lessonRepository.delete(this.find(id));
    }
    
    @Override
    public void disable(long id) {
        LessonEntity lesson = this.find(id);
        lesson.setActive(false); 
        this.lessonRepository.save(lesson);
    }
    @Override
    public Page<LessonResp> getAll(int page, int size, SortType sort) {
         if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.lessonRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    private LessonResp entityToResponse(LessonEntity entity){
        return LessonResp.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .content(entity.getContent())
        .active(entity.getActive())
        .createdAt(LocalDate.now())
        .multimedia(entity.getMultimedia())
        .build();
    }

    private LessonEntity requestToEntity (LessonReq request){
        return LessonEntity.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .active(request.getActive())
        .build();
    }

    private LessonEntity find(Long id){
        return this.lessonRepository.findById(id).orElseThrow(()->new com.riwi.filtro.utils.exceptions.BadRequestException("Not found"));
    }

}
