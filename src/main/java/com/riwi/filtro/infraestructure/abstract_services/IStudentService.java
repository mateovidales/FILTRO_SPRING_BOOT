package com.riwi.filtro.infraestructure.abstract_services;

import org.springframework.data.domain.Page;
import com.riwi.filtro.api.dto.request.StudentReq;
import com.riwi.filtro.api.dto.response.StudentResp;
import com.riwi.filtro.infraestructure.CrudService;
import com.riwi.filtro.utils.enums.SortType;

public interface IStudentService extends CrudService<StudentReq, StudentResp, Long> {
     public Page<StudentResp> findByNameContainingAndActive(int page, int size, SortType sort, String name, Boolean active);
      StudentResp disable(long id);
     public final String FIELD_BY_SORT = "student";
}
