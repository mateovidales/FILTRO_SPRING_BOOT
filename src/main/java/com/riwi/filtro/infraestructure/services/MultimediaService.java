package com.riwi.filtro.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.riwi.filtro.api.dto.request.MultimediaReq;
import com.riwi.filtro.api.dto.response.MultimediaResp;
import com.riwi.filtro.domain.entities.MultimediaEntity;
import com.riwi.filtro.domain.repositories.MultimediaRepository;
import com.riwi.filtro.infraestructure.abstract_services.IMultimediaService;
import com.riwi.filtro.utils.enums.SortType;
import com.riwi.filtro.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MultimediaService implements IMultimediaService {
    @Autowired
    private final MultimediaRepository multimediaRepository;

    @Override
    public MultimediaResp create(MultimediaReq request) {
    MultimediaEntity multi = this.requestToEntity(request);
    return this.entityToResponse(this.multimediaRepository.save(multi));
    }

    @Override
    public MultimediaResp get(Long id) {
        return this.entityToResponse(this.find(id));
        
    }

    @Override
    public MultimediaResp update(MultimediaReq request, Long id) {
        MultimediaEntity multi = this.find(id);
        multi = this.requestToEntity(request);
        multi.setId(id);

        return this.entityToResponse(this.multimediaRepository.save(multi));
    }

    @Override
    public void delete(Long id) {
        this.multimediaRepository.delete(this.find(id));
    }

    @Override
    public Page<MultimediaResp> getAll(int page, int size, SortType sort) {
         if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.multimediaRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    private MultimediaResp entityToResponse(MultimediaEntity entity){
        return MultimediaResp.builder()
        .id(entity.getId())
        .type(entity.getType())
        .url(entity.getUrl())
        .createdAt(entity.getCreatedAt())
        .active(entity.getActive())
        .build();
    }

    private MultimediaEntity requestToEntity (MultimediaReq request){
        return MultimediaEntity.builder()
        .type(request.getType())
        .url(request.getUrl())
        .active(request.getActive())
        .build();
    }

    private MultimediaEntity find(Long id){
        return this.multimediaRepository.findById(id).orElseThrow(()->new BadRequestException("Not found"));
    }

    
}
