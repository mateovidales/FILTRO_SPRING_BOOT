package com.riwi.filtro.infraestructure.abstract_services;

import com.riwi.filtro.api.dto.request.LessonReq;
import com.riwi.filtro.api.dto.response.LessonResp;
import com.riwi.filtro.infraestructure.CrudService;

public interface ILessonService extends CrudService<LessonReq, LessonResp, Long> {
    public void disable(long id);
    public final String FIELD_BY_SORT = "lesson";
}
