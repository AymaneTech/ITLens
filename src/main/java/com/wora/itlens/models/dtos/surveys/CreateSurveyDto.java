package com.wora.itlens.models.dtos.surveys;

import jakarta.validation.constraints.NotBlank;

public record CreateSurveyDto(
        @NotBlank String title,
        @NotBlank String description
) {
}