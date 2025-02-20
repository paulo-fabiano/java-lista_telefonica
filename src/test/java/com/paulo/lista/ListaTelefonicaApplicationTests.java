package com.paulo.lista;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.paulo.lista.entity.Contato;
import com.paulo.lista.repository.ContatoRepository;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TesteDeIntegracao {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ContatoRepository contatoRepository;

	@Test
	void deveSalvarContatoNoBanco() {
		// Arrange (Criando o objeto)
		Contato contato = new Contato(null, "Paulo", "123456789", "test@gmail.com");

		// Act (Salvando no banco)
		Contato salvo = contatoRepository.save(contato);

		// Assert (Verificando se foi salvo corretamente)
		assertNotNull(salvo.getId()); // O ID deve ser gerado
		assertEquals("Paulo", salvo.getNome());
		assertEquals("123456789", salvo.getTelefone());
		assertEquals("test@gmail.com", salvo.getEmail());

		// Confirmando que o banco tem 1 registro
		assertEquals(1, contatoRepository.count());
	}
}
