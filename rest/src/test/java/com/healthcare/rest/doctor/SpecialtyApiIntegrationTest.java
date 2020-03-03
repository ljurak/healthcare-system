package com.healthcare.rest.doctor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.rest.user.dto.JwtTokenDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecialtyApiIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private String token;
	
	@BeforeEach
	public void setUp() throws Exception {
		String username = "admin";
		String password = "admin";
		String requestBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		
		MvcResult result = mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andReturn();
		
		token = objectMapper.readValue(result.getResponse().getContentAsString(), JwtTokenDTO.class).getToken();
	}
	
	@Test
	public void shouldReturnListOfSpecialtiesWhenSendingGetRequest() throws Exception {
		// when
		mockMvc.perform(get("/specialties").header("Authorization", "Bearer " + token))
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(10)))
		.andExpect(jsonPath("$[1].name", is("dermatology")));
	}
}
