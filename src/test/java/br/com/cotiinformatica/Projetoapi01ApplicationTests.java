package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.EmpresasPostDto;

@SpringBootTest
@AutoConfigureMockMvc
class Projetoapi01ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ1c3Vhcmlvc2FwaSIsInN1YiI6InNlcmdpby5jb3RpQGFvbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjY4MDQxNDE3LCJleHAiOjE2NjgwNDc0MTd9.xFQdMvo7IqPT737NbkscuMnClXpKFPjR6sEq6NQRAe7f49rdCQbVDI531JoF7CDGMsHZU2HgR1LWVuBV70xw2w";
	
	@Test
	public void postEmpresasTest() throws Exception {
	
		Faker faker = new Faker(new Locale("pt-BR"));
		
		EmpresasPostDto dto = new EmpresasPostDto();
		dto.setNomeFantasia(faker.company().name());
		dto.setRazaoSocial(faker.company().name());
		dto.setCnpj(String.valueOf(faker.random().nextInt(999999999)));
		
		mockMvc.perform(
				post("/api/empresas")	
				.header("authorization", "Bearer " + token)
				.contentType("application/json")
				.content
					(objectMapper.writeValueAsString(dto)))
			.andExpect(status().isCreated());	
	}
}
