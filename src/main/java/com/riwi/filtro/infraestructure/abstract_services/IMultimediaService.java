package com.riwi.filtro.infraestructure.abstract_services;

import com.riwi.filtro.api.dto.request.MultimediaReq;
import com.riwi.filtro.api.dto.response.MultimediaResp;
import com.riwi.filtro.infraestructure.CrudService;

public interface IMultimediaService extends CrudService<MultimediaReq, MultimediaResp, Long>{
    public final String FIELD_BY_SORT = "multimedia";
}
