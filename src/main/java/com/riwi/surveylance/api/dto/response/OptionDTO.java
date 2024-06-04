package com.riwi.surveylance.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {

    private int id;

    private String text;

    private Boolean active;

    private int question_id;

}
