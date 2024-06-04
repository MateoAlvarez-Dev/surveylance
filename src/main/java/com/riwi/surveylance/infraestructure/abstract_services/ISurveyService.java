package com.riwi.surveylance.infraestructure.abstract_services;

import com.riwi.surveylance.api.dto.request.SurveyRequest;
import com.riwi.surveylance.api.dto.response.SurveyResponse;
import com.riwi.surveylance.api.dto.response.SurveyResponseFull;

public interface ISurveyService extends CRUDService<SurveyRequest, SurveyResponse, Integer>{
    public SurveyResponseFull getByIdFull(Integer id);
}
