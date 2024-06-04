package com.riwi.surveylance.infraestructure.abstract_services;

import com.riwi.surveylance.api.dto.request.QuestionPatch;
import com.riwi.surveylance.api.dto.request.QuestionRequest;
import com.riwi.surveylance.api.dto.response.QuestionResponse;

public interface IQuestionService extends CRUDService<QuestionRequest, QuestionResponse, Integer>{
    public QuestionResponse updateFull(Integer id, QuestionRequest request);
    public QuestionResponse update(Integer id, QuestionPatch request);
}
