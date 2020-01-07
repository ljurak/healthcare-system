package com.healthcare.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class SpecialtyApiIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnListOfSpecialtiesWhenSendingGetRequest() throws Exception {
		// when
		mockMvc.perform(get("/specialties"))
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(10)))
		.andExpect(jsonPath("$[1].name", is("dermatology")));
	}
}
