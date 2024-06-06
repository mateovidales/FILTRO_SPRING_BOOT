package com.riwi.filtro.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.filtro.api.dto.request.ClassReq;
import com.riwi.filtro.api.dto.response.ClassResp;
import com.riwi.filtro.infraestructure.CrudService;
import com.riwi.filtro.utils.enums.SortType;

public interface IClassService extends CrudService<ClassReq, ClassResp, Long>{
 public Page<ClassResp> findByNameAndDescription(int page, int size, SortType sort, String name, String description);    
    public final String FIELD_BY_SORT = "class";
}
