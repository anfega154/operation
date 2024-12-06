package com.quasar.operation;

import com.quasar.operation.aplication.usecase.DetermineMessageAndLocation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuasarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DetermineMessageAndLocation determineMessageAndLocation;

    @Test
    public void testTopSecretSuccess() throws Exception {
        DetermineMessageAndLocation.DeterminationResult result =
                new DetermineMessageAndLocation.DeterminationResult(new java.awt.Point(-100, 75), "este es un mensaje secreto");

        when(determineMessageAndLocation.execute(anyList())).thenReturn(result);

        mockMvc.perform(post("/api/topsecret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"satellites\": [{\"name\": \"kenobi\", \"distance\": 100.0, \"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"]}, " +
                                "{\"name\": \"skywalker\", \"distance\": 115.5, \"message\": [\"\", \"es\", \"\", \"\", \"secreto\"]}, " +
                                "{\"name\": \"sato\", \"distance\": 142.7, \"message\": [\"este\", \"\", \"un\", \"\", \"\"]}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(-100.0))
                .andExpect(jsonPath("$.position.y").value(75.0))
                .andExpect(jsonPath("$.message").value("este es un mensaje secreto"));
    }

    @Test
    public void testTopSecretFailure() throws Exception {
        when(determineMessageAndLocation.execute(anyList()))
                .thenThrow(new IllegalArgumentException("Error en la determinación"));

        mockMvc.perform(post("/api/topsecret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"satellites\": [{\"name\": \"kenobi\", \"distance\": 100.0, \"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"]}, " +
                                "{\"name\": \"skywalker\", \"distance\": 115.5, \"message\": [\"\", \"es\", \"\", \"\", \"secreto\"]}, " +
                                "{\"name\": \"sato\", \"distance\": 142.7, \"message\": [\"este\", \"\", \"un\", \"\", \"\"]}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Error en la determinación"));
    }
}