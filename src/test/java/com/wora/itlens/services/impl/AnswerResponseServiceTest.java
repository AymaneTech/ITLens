package com.wora.itlens.services.impl;

import com.wora.itlens.mappers.AnswerResponseMapper;
import com.wora.itlens.models.dtos.answerResponses.AnswerResponseDto;
import com.wora.itlens.models.dtos.answerResponses.CreateMultipleAnswersAndOneQuestionDto;
import com.wora.itlens.models.dtos.answerResponses.QuestionWithAnswersResponseDto;
import com.wora.itlens.models.dtos.answers.AnswerDto;
import com.wora.itlens.models.dtos.answers.EmbeddedAnswerDto;
import com.wora.itlens.models.dtos.questions.EmbeddedQuestionDto;
import com.wora.itlens.models.entites.Answer;
import com.wora.itlens.models.entites.Question;
import com.wora.itlens.models.enumes.QuestionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerResponseServiceTest {
    @Mock
    private AnswerService answerService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerResponseMapper answerResponseMapper;

    @InjectMocks
    private AnswerResponseService answerResponseService;


    @Test
    @DisplayName("createAnswersForQuestion() Should increment selection and answer counts")
    void createAnswersForQuestion_shouldIncrementCountsSuccessfully() {
        Long questionId = 1L;
        CreateMultipleAnswersAndOneQuestionDto dto = new CreateMultipleAnswersAndOneQuestionDto(List.of(new AnswerDto(1L, "Hamza", 1), new AnswerDto(2L, "LAMIN", 2)), questionId);

        Question mockQuestion = new Question();
        mockQuestion.setAnswerCount(2);

        Answer mockAnswer = new Answer();
        mockAnswer.setSelectionCount(3);

        when(questionService.getQuestionEntity(questionId)).thenReturn(mockQuestion);
        when(answerService.getAnswerEntity(anyLong())).thenReturn(mockAnswer);
        when(answerService.saveAnswerEntity(any(Answer.class))).thenReturn(mockAnswer);
        when(questionService.saveQuestionEntity(any(Question.class))).thenReturn(mockQuestion);

        answerResponseService.createAnswersForQuestion(dto);

        verify(questionService).getQuestionEntity(questionId);

        for (AnswerDto answerDto : dto.answers()) {
            verify(answerService).getAnswerEntity(answerDto.id());
        }

        verify(answerService, times(dto.answers().size())).saveAnswerEntity(mockAnswer);

        verify(questionService).saveQuestionEntity(mockQuestion);

        assertEquals(4, mockQuestion.getAnswerCount());
        assertEquals(5, mockAnswer.getSelectionCount());
    }


}