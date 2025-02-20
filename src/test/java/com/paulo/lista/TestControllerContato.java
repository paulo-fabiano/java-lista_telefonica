package com.paulo.lista;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType; // ✅ Importação correta
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulo.lista.entity.Contato;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestControllerContato {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarContatoNoBanco() throws Exception { 

        // Arrange (Criando o objeto)
        Contato contato = Contato.builder()
                .id(null)
                .nome("Paulo")
                .telefone("123456789")
                .email("test@gmail.com")
                .build();
                
        String json = objectMapper.writeValueAsString(contato);

        // Act (Executando a requisição)
        mockMvc.perform(post("/contato/criar")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Paulo"));
    }
}
