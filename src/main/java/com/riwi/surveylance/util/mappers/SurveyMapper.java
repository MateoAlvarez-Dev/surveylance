package com.riwi.surveylance.util.mappers;

import com.riwi.surveylance.api.dto.request.SurveyRequest;
import com.riwi.surveylance.api.dto.response.SurveyResponse;
import com.riwi.surveylance.api.dto.response.SurveyResponseFull;
import com.riwi.surveylance.domain.entities.Survey;
import com.riwi.surveylance.domain.entities.User;

public class SurveyMapper {
    public SurveyResponse entityToResponse(Survey entity){
        SurveyResponse response = SurveyResponse.builder()
                                .id(entity.getId())
                                .title(entity.getTitle())
                                .description(entity.getDescription())
                                .creationDate(entity.getCreationDate())
                                .active(entity.getActive())
                                .creator_id(entity.getCreator_id().getId())
                                .build();
        return response;
    }

    public SurveyResponseFull entityToResponseFull(Survey entity){
        UserMapper userMapper = new UserMapper();
        QuestionMapper questionMapper = new QuestionMapper();

        SurveyResponseFull response = SurveyResponseFull.builder()
                                .id(entity.getId())
                                .title(entity.getTitle())
                                .description(entity.getDescription())
                                .creationDate(entity.getCreationDate())
                                .active(entity.getActive())
                                .creator(userMapper.entityToResponse(entity.getCreator_id()))
                                .questions(questionMapper.multipleQuestionsToResponse(entity.getQuestions()))
                                .build();
    
        return response;
    }

    public Survey requestToEntity(SurveyRequest request){
        User creator = User.builder().id(request.getCreator_id()).build();
        Survey entity = Survey.builder()
                      .title(request.getTitle())
                      .description(request.getDescription())
                      .active(request.getActive())
                      .creator_id(creator)
                      .build();

        return entity;
    }
}
