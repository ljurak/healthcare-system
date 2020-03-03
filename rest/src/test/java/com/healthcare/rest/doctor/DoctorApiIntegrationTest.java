package com.healthcare.rest.doctor;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.rest.doctor.Doctor;
import com.healthcare.rest.doctor.DoctorRepo;
import com.healthcare.rest.user.dto.JwtTokenDTO;

@SpringBootTest
@AutoConfigureMockMvc
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
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private String token;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
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
	public void shouldReturnListOfDoctorsWhenSendingGetRequest() throws Exception {
		// when
		mockMvc.perform(get("/doctors").header("Authorization", "Bearer " + token))
			
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
				.content(validAddDoctorJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Greg")))
		.andExpect(jsonPath("$.email", is(nullValue())));
		
		Doctor doctor = doctorRepo.findByLastNameContainingIgnoreCaseOrderByLastName("Monty").get(0);
		assertEquals("Greg", doctor.getFirstName());
		assertEquals(LocalDate.of(1987, 3, 12), doctor.getBirthDate());
		assertNull(doctor.getEmail());
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidDoctorPostRequest() throws Exception {
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidAddDoctorJsonString)
				.header("Authorization", "Bearer " + token))
		
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
				.content(notReadableDateJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is("Malformed JSON request")))
		.andExpect(jsonPath("$.fieldErrors", is(nullValue())));
	}
	
	@Test
	public void shouldReturnDoctorWhenSendingGetRequestWithId() throws Exception {
		// when
		mockMvc.perform(get("/doctors/{id}", 1L).header("Authorization", "Bearer " + token))
		
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
				.param("lastname", "oo")
				.header("Authorization", "Bearer " + token))
		
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
		mockMvc.perform(get("/doctors/{id}", 25L).header("Authorization", "Bearer " + token))
		
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
				.content(validUpdateDoctorJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Coltrane")))
		.andExpect(jsonPath("$.phoneNumber", is("1234567890")))
		.andExpect(jsonPath("$.email", is("example@mail.com")));
		
		Doctor doctor = doctorRepo.findById(1L).get();
		assertEquals("Coltrane", doctor.getFirstName());
		assertEquals("1234567890", doctor.getPhoneNumber());
		assertEquals("example@mail.com" ,doctor.getEmail());
	}
	
	@Test
	public void shouldReturnListOfDoctorsVisitsWhenSendingGetRequestWithParameters() throws Exception {		
		// when
		mockMvc.perform(get("/doctors/3/visits")
				.param("startDate", "2019-10-12")
				.param("endDate", "2020-02-17")
				.header("Authorization", "Bearer " + token))
		
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
				.param("endDate", "2020-02-1")
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isBadRequest());
	}
}
