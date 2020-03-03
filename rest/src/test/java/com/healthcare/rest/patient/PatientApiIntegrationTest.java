package com.healthcare.rest.patient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import com.healthcare.rest.user.dto.JwtTokenDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientApiIntegrationTest {
	
	private final String validAddPatientJsonString = "{"
			+ "\"firstName\":\"Mike\","
			+ "\"lastName\":\"Kent\","
			+ "\"birthDate\":\"1992-04-19\","
			+ "\"address\":\"345 Valid Road\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";
	
	private final String validUpdatePatientJsonString = "{"
			+ "\"firstName\":\"Clementine\","
			+ "\"lastName\":\"Bauch\","
			+ "\"birthDate\":\"1981-05-21\","
			+ "\"address\":\"345 Valid Road\","
			+ "\"phoneNumber\":\"1234567890\","
			+ "\"email\":\"example@mail.com\"}";
	
	private final String invalidAddPatientJsonString = "{"
			+ "\"firstName\":\"Mike\","
			+ "\"birthDate\":\"1992-04-19\","
			+ "\"address\":\"345 Valid Road\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";
	
	private final String notReadableDateJsonString = "{"
			+ "\"firstName\":\"Mike\","
			+ "\"lastName\":\"Kent\","
			+ "\"birthDate\":\"19ec-0h-19\","
			+ "\"address\":\"345 Valid Road\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";
	
	private final String validAddVisitJsonString = "{"
			+ "\"patientId\":8,"
			+ "\"doctorId\":3,"
			+ "\"visitDate\":\"2021-10-24\","
			+ "\"visitTime\":\"12:00\"}";
	
	private final String unavailableAddVisitJsonString = "{"
			+ "\"patientId\":8,"
			+ "\"doctorId\":11,"
			+ "\"visitDate\":\"2020-03-14\","
			+ "\"visitTime\":\"12:00\"}";
	
	private final String invalidAddVisitJsonString = "{"
			+ "\"patientId\":8,"
			+ "\"visitDate\":\"2021-10-24\","
			+ "\"visitTime\":\"12:00\"}";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PatientRepo patientRepo;
	
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
	public void shouldReturnListOfPatientsWhenSendingGetRequest() throws Exception {		
		// when
		mockMvc.perform(get("/patients").header("Authorization", "Bearer " + token))
			
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(10)))
		.andExpect(jsonPath("$[0].lastName", is("Bauch")));
	}
	
	@Test
	@DirtiesContext
	public void shouldReturn201WhenSendingValidPatientPostRequest() throws Exception {		
		// when
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validAddPatientJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Mike")))
		.andExpect(jsonPath("$.email", is(nullValue())));
		
		Patient patient = patientRepo.findByLastNameContainingIgnoreCaseOrderByLastName("Kent").get(0);
		assertEquals("Mike", patient.getFirstName());
		assertEquals(LocalDate.of(1992, 4, 19), patient.getBirthDate());
		assertNull(patient.getEmail());
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidPatientPostRequest() throws Exception {		
		// when
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidAddPatientJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", hasSize(1)))
		.andExpect(jsonPath("$.message", is("Invalid data format")))
		.andExpect(jsonPath("$.fieldErrors[0].field", is("lastName")));
	}
	
	@Test
	public void shouldReturn400WhenSendingNotReadableJsonPostRequest() throws Exception {		
		// when
		mockMvc.perform(post("/patients")
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
	public void shouldReturnPatientWhenSendingGetRequestWithId() throws Exception {		
		// when
		mockMvc.perform(get("/patients/{id}", 1L).header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.lastName", is("Bauch")));
	}
	
	@Test
	public void shouldReturnListOfPatientsByLastnameWhenSendingGetRequestWithParameter() throws Exception {		
		// when
		mockMvc.perform(get("/patients")
				.param("lastname", "ei")
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].lastName", is("Reichert")))
		.andExpect(jsonPath("$[1].lastName", is("Weissnat")));
	}
	
	@Test
	public void shouldReturn404WhenSendingGetRequestAndPatientDoesNotExist() throws Exception {		
		// when
		mockMvc.perform(get("/patients/{id}", 25L).header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isNotFound())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is("Patient with id: 25 does not exist")));
	}
	
	@Test
	@DirtiesContext
	public void shouldReturn200WhenSendingUpdatePatientPutRequest() throws Exception {		
		// when
		mockMvc.perform(put("/patients/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(validUpdatePatientJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", is("Clementine")))
		.andExpect(jsonPath("$.phoneNumber", is("1234567890")))
		.andExpect(jsonPath("$.email", is("example@mail.com")));
		
		Patient patient = patientRepo.findById(1L).get();
		assertEquals("Clementine", patient.getFirstName());
		assertEquals("1234567890", patient.getPhoneNumber());
		assertEquals("example@mail.com", patient.getEmail());
	}
	
	@Test
	@DirtiesContext
	public void shouldReturn201WhenSendingValidVisitPostRequest() throws Exception {		
		// when
		mockMvc.perform(post("/patients/8/visits")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validAddVisitJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.patientId", is(8)))
		.andExpect(jsonPath("$.doctorName", is("Katy Pank")));
	}
	
	@Test
	public void shouldReturn400WhenSendingValidButUnavailableVisitPostRequest() throws Exception {
		// when
		mockMvc.perform(post("/patients/8/visits")
				.contentType(MediaType.APPLICATION_JSON)
				.content(unavailableAddVisitJsonString)
				.header("Authorization", "Bearer " + token))
			
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is("Visit at given time is not available")));
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidVisitPostRequest() throws Exception {		
		// when
		mockMvc.perform(post("/patients/8/visits")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidAddVisitJsonString)
				.header("Authorization", "Bearer " + token))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", hasSize(1)))
		.andExpect(jsonPath("$.message", is("Invalid data format")))
		.andExpect(jsonPath("$.fieldErrors[0].field", is("doctorId")));
	}
}
