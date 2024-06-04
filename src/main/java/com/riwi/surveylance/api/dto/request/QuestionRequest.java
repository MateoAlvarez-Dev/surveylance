package com.riwi.surveylance.api.dto.request;

import java.util.List;

import com.riwi.surveylance.api.dto.response.OptionDTO;
import com.riwi.surveylance.util.enums.QuestionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    
    private int id;

    @NotBlank(message = "The text is required")
    private String text;

    private QuestionType type;

    @NotNull(message = "The status is required")
    private Boolean active;

    @NotNull(message = "The survey id is required")
    private int survey_id;

    List<OptionDTO> options;


}
