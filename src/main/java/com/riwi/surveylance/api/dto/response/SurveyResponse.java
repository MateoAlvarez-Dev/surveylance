package com.riwi.surveylance.api.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {

    private int id;

    private String title;

    private String description;

    private Date creationDate;

    private Boolean active;

    private int creator_id;
    
}
