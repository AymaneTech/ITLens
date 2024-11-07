package com.wora.itlens.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wora.itlens.mappers.SubjectMapper;
import com.wora.itlens.models.dtos.subjects.CreateSubjectDto;
import com.wora.itlens.models.entites.Subject;
import com.wora.itlens.repositories.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void create_Successfully() throws Exception {
        Subject parentSubject = new Subject();
        parentSubject.setTitle("Parent Subject");
        parentSubject = subjectRepository.save(parentSubject);

        CreateSubjectDto dto = new CreateSubjectDto("Title Nooormal", 1L, parentSubject.getId());
        String requestJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }


}