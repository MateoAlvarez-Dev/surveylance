package com.riwi.surveylance.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.surveylance.api.dto.request.QuestionPatch;
import com.riwi.surveylance.api.dto.request.QuestionRequest;
import com.riwi.surveylance.api.dto.response.QuestionResponse;
import com.riwi.surveylance.domain.entities.OptionQuestion;
import com.riwi.surveylance.domain.entities.Question;
import com.riwi.surveylance.domain.repositories.OptionRepository;
import com.riwi.surveylance.domain.repositories.QuestionRepository;
import com.riwi.surveylance.infraestructure.abstract_services.IQuestionService;
import com.riwi.surveylance.util.enums.QuestionType;
import com.riwi.surveylance.util.exceptions.IdNotFoundException;
import com.riwi.surveylance.util.exceptions.NoOptionsInClosedQuestion;
import com.riwi.surveylance.util.mappers.QuestionMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService extends QuestionMapper implements IQuestionService{

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionRepository optionRepository;

     @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.questionRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question questionToSave = this.requestToEntity(request);
        Question questionSaved = this.questionRepository.save(questionToSave);

        if(request.getType().equals(QuestionType.CLOSED)){
            if(request.getOptions().isEmpty()) throw new NoOptionsInClosedQuestion();
            request.getOptions().forEach((Option) -> {
                Option.setQuestion_id(questionSaved.getId());
                this.optionRepository.save(this.optionRequestToOption(Option));
            });
        }

        questionSaved.setOptions(new ArrayList<>());
        QuestionResponse response = this.entityToResponse(questionSaved);

        return response;
    }

    @Override
    public QuestionResponse updateFull(Integer id, QuestionRequest request) {
        this.findById(id);

        Question questionToSave = this.requestToEntity(request);

        questionToSave.setId(id);

        if(request.getType().equals(QuestionType.CLOSED)){
            if(request.getOptions().isEmpty()) throw new NoOptionsInClosedQuestion();
            request.getOptions().forEach((Option) -> {
                Option.setQuestion_id(id);
                OptionQuestion optionToSave = this.optionRequestToOption(Option);
                optionToSave.setId(Option.getId());
                this.optionRepository.save(optionToSave);
            });
        }

        Question questionSaved = this.questionRepository.save(questionToSave);

        questionSaved.setOptions(new ArrayList<>());

        return this.entityToResponse(questionSaved);
    }

    @Override
    public QuestionResponse update(Integer id, QuestionPatch request) {
        Question questionFromDatabase = this.findById(id);
        questionFromDatabase.setText(request.getText());
        Question questionUpdated = this.questionRepository.save(questionFromDatabase);
        QuestionResponse response = this.entityToResponse(questionUpdated);

        return response;
    }

    @Override
    public void delete(Integer id) {
        Question questionToDelete = this.findById(id);
        this.questionRepository.delete(questionToDelete);
    }

    @Override
    public QuestionResponse getById(Integer id) {
        return this.entityToResponse(this.findById(id));
    }

    // UTILS

    private Question findById(Integer id){
        return this.questionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Question", id));
    }

    @Override
    public QuestionResponse update(Integer id, QuestionRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method, this was replaced by the another update method");
    }

    
}
