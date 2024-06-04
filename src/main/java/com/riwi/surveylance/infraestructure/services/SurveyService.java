package com.riwi.surveylance.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.surveylance.api.dto.request.SurveyRequest;
import com.riwi.surveylance.api.dto.response.SurveyResponse;
import com.riwi.surveylance.api.dto.response.SurveyResponseFull;
import com.riwi.surveylance.domain.entities.Survey;
import com.riwi.surveylance.domain.repositories.SurveyRepository;
import com.riwi.surveylance.infraestructure.abstract_services.ISurveyService;
import com.riwi.surveylance.util.exceptions.IdNotFoundException;
import com.riwi.surveylance.util.mappers.SurveyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService extends SurveyMapper implements ISurveyService{
    
    @Autowired
    private final SurveyRepository surveyRepository;

     @Override
    public Page<SurveyResponse> getAll(int page, int size) {
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.surveyRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public SurveyResponse create(SurveyRequest request) {
        Survey surveyToSave = this.requestToEntity(request);
        Survey surveySaved = this.surveyRepository.save(surveyToSave);
        SurveyResponse response = this.entityToResponse(surveySaved);

        return response;
    }

    @Override
    public SurveyResponse update(Integer id, SurveyRequest request) {
        Survey surveyFromDatabase = this.findById(id);

        Survey surveyToUpdate = this.requestToEntity(request);
        surveyToUpdate.setId(id);
        surveyToUpdate.setCreationDate(surveyFromDatabase.getCreationDate());
        Survey surveyUpdated = this.surveyRepository.save(surveyToUpdate);
        SurveyResponse response = this.entityToResponse(surveyUpdated);

        return response;
    }

    @Override
    public void delete(Integer id) {
        Survey surveyToDelete = this.findById(id);
        this.surveyRepository.delete(surveyToDelete);
    }

    @Override
    public SurveyResponse getById(Integer id) {
        return this.entityToResponse(this.findById(id));
    }

    @Override
    public SurveyResponseFull getByIdFull(Integer id) {
        return this.entityToResponseFull(this.findById(id));
    }

    // UTILS

    private Survey findById(Integer id){
        return this.surveyRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Survey", id));
    }


}
