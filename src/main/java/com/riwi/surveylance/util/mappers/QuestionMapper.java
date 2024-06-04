package com.riwi.surveylance.util.mappers;

import java.util.ArrayList;
import java.util.List;

import com.riwi.surveylance.api.dto.request.QuestionRequest;
import com.riwi.surveylance.api.dto.response.OptionDTO;
import com.riwi.surveylance.api.dto.response.QuestionResponse;
import com.riwi.surveylance.domain.entities.OptionQuestion;
import com.riwi.surveylance.domain.entities.Question;
import com.riwi.surveylance.domain.entities.Survey;

public class QuestionMapper {
    public QuestionResponse entityToResponse(Question entity){
        QuestionResponse response = QuestionResponse.builder()
                                .id(entity.getId())
                                .text(entity.getText())
                                .type(entity.getType())
                                .active(entity.getActive())
                                .survey_id(entity.getSurvey_id().getId())
                                .options(this.getQuestionOptions(entity))
                                .build();
        return response;
    }

    public Question requestToEntity(QuestionRequest request){
        Survey survey = Survey.builder().id(request.getSurvey_id()).build();
        Question entity = Question.builder()
                      .text(request.getText())
                      .type(request.getType())
                      .active(request.getActive())
                      .survey_id(survey)
                      .build();

        return entity;
    }

    public OptionDTO optionToOptionResponse(OptionQuestion option){
        OptionDTO response = OptionDTO.builder()
                                  .id(option.getId())
                                  .text(option.getText())
                                  .active(option.getActive())
                                  .question_id(option.getQuestion_id().getId())
                                  .build();
        return response;
    }

    public OptionQuestion optionRequestToOption(OptionDTO request){
        Question question = Question.builder().id(request.getQuestion_id()).build();
        OptionQuestion option = OptionQuestion.builder()
                                .text(request.getText())
                                .active(request.getActive())
                                .question_id(question)
                                .build();
        return option;
    }

    public List<QuestionResponse> multipleQuestionsToResponse(List<Question> questions){
        List<QuestionResponse> responseQuestions = new ArrayList<>();
        questions.forEach((Question) -> responseQuestions.add(this.entityToResponse(Question)));
        return responseQuestions;
    }

    public List<OptionDTO> getQuestionOptions(Question question){
        List<OptionDTO> options = new ArrayList<>();
        question.getOptions().forEach((OptionQuestion) -> options.add(this.optionToOptionResponse(OptionQuestion)));
        return options;
    }
}
