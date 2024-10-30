package com.wora.itlens.models.dtos.subjects;

import com.wora.itlens.models.dtos.questions.EmbeddedQuestionDto;
import com.wora.itlens.models.entites.SurveyEdition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record EmbeddedSubjectDto(
        @NotNull @Positive Long id,
        @NotBlank String title,
        SurveyEdition surveyEdition,
        EmbeddedSubjectDto subject,
        List<EmbeddedQuestionDto> questions
) {
}