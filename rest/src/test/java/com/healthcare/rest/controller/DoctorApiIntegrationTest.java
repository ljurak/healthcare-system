package com.healthcare.rest.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DoctorApiIntegrationTest {
	
	private final String validAddDoctorJsonString = "{"
			+ "\"firstName\":\"Greg\","
			+ "\"lastName\":\"Monty\","
			+ "\"birthDate\":\"1987-03-12\","
			+ "\"address\":\"345 Valid Route\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null,"
			+ "\"specialty\":\"dermatology\"}";
	
	private final String validUpdateDoctorJsonString = "{"
			+ "\"firstName\":\"Coltrane\","
			+ "\"lastName\":\"Wood\","
			+ "\"birthDate\":\"1982-06-15\","
			+ "\"address\":\"345 Valid Route\","
			+ "\"phoneNumber\":\"1234567890\","
			+ "\"email\":\"example@mail.com\","
			+ "\"specialty\":\"dermatology\"}";
	
	private final String invalidAddDoctorJsonString = "{"
			+ "\"firstName\":\"Greg\","
			+ "\"birthDate\":\"1987-03-12\","
			+ "\"address\":\"345 Valid Route\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";
	
	private final String notReadableDateJsonString = "{"
			+ "\"firstName\":\"Greg\","
			+ "\"lastName\":\"Monty\","
			+ "\"birthDate\":\"19ec-0h-1i\","
			+ "\"address\":\"345 Valid Route\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnListOfDoctorsWhenSendingGetRequest() throws Exception {
		// when
		mockMvc.perform(get("/doctors"))
			
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(12)))
		.andExpect(jsonPath("$[0].lastName", is("Arpag")));
	}
	
	@Test
	@DirtiesContext
	public void shouldReturn201WhenSendingValidDoctorPostRequest() throws Exception {
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validAddDoctorJsonString))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Greg")))
		.andExpect(jsonPath("$.email", is(nullValue())));
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidDoctorPostRequest() throws Exception {
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidAddDoctorJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", hasSize(2)))
		.andExpect(jsonPath("$.message", is("Invalid data format")))
		.andExpect(jsonPath("$.fieldErrors[0].field", is(in(List.of("lastName", "specialty")))));
	}
	
	@Test
	public void shouldReturn400WhenSendingNotReadableJsonPostRequest() throws Exception {
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(notReadableDateJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is("Malformed JSON request")))
		.andExpect(jsonPath("$.fieldErrors", is(nullValue())));
	}
	
	@Test
	public void shouldReturnDoctorWhenSendingGetRequestWithId() throws Exception {
		// when
		mockMvc.perform(get("/doctors/{id}", 1L))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.lastName", is("Wood")));
	}
	
	@Test
	public void shouldReturnListOfDoctorsByLastnameWhenSendingGetRequestWithParameter() throws Exception {		
		// when
		mockMvc.perform(get("/doctors")
				.param("lastname", "oo"))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].lastName", is("Moody")))
		.andExpect(jsonPath("$[1].lastName", is("Wood")));
	}
	
	@Test
	public void shouldReturn404WhenSendingGetRequestAndDoctorDoesNotExist() throws Exception {		
		// when
		mockMvc.perform(get("/doctors/{id}", 25L))
		
		// then
		.andExpect(status().isNotFound())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is("Doctor with id: 25 does not exist")));
	}
	
	@Test
	@DirtiesContext
	public void shouldReturn200WhenSendingUpdateDoctorPutRequest() throws Exception {		
		// when
		mockMvc.perform(put("/doctors/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(validUpdateDoctorJsonString))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Coltrane")))
		.andExpect(jsonPath("$.phoneNumber", is("1234567890")))
		.andExpect(jsonPath("$.email", is("example@mail.com")));
	}
	
	@Test
	public void shouldReturnListOfDoctorsVisitsWhenSendingGetRequestWithParameters() throws Exception {		
		// when
		mockMvc.perform(get("/doctors/3/visits")
				.param("startDate", "2019-10-12")
				.param("endDate", "2020-02-17"))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].visitDate", is("2019-12-12")))
		.andExpect(jsonPath("$[0].visitTime", is("12:00:00")));
	}
	
	@Test
	public void shouldReturn400WhenSendingGetRequestWithInvalidParameters() throws Exception {		
		// when
		mockMvc.perform(get("/doctors/3/visits")
				.param("startDate", "2019-1-12")
				.param("endDate", "2020-02-1"))
		
		// then
		.andExpect(status().isBadRequest());
	}
}
