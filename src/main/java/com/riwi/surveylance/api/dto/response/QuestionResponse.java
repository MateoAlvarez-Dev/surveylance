package com.riwi.surveylance.api.dto.response;

import java.util.List;

import com.riwi.surveylance.util.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    private int id;

    private String text;

    private QuestionType type;

    private Boolean active;

    private int survey_id;

    private List<OptionDTO> options;

}
