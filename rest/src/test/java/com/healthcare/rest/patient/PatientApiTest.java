package com.healthcare.rest.patient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.healthcare.rest.patient.dto.PatientDTO;
import com.healthcare.rest.patient.exception.PatientNotFoundException;
import com.healthcare.rest.user.SecurityConfig;
import com.healthcare.rest.visit.dto.VisitDTO;

@WebMvcTest(
	controllers = PatientApi.class, 
	excludeAutoConfiguration = SecurityAutoConfiguration.class, 
	excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE, 
		classes = { SecurityConfig.class }
	)
)
public class PatientApiTest {
	
	private final String validPatientJsonString = "{"
			+ "\"firstName\":\"Mike\","
			+ "\"lastName\":\"Kent\","
			+ "\"birthDate\":\"1992-04-19\","
			+ "\"address\":\"345 Valid Road\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null}";
	
	private final String invalidPatientJsonString = "{"
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
	
	private final String validVisitJsonString = "{"
			+ "\"patientId\":\"8\","
			+ "\"doctorId\":\"3\","
			+ "\"visitDate\":\"2021-10-24\","
			+ "\"visitTime\":\"12:00\"}";
	
	private final String invalidVisitJsonString = "{"
			+ "\"patientId\":\"8\","
			+ "\"visitDate\":\"2021-10-24\","
			+ "\"visitTime\":\"12:00\"}";
	
	private static PatientDTO patientDTO;
	
	private static VisitDTO visitDTO;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientFacade patientFacade;
	
	@BeforeAll
	public static void init() {	
		patientDTO = new PatientDTO();
		patientDTO.setId(1L);
		patientDTO.setFirstName("Mike");
		patientDTO.setLastName("Kent");
		patientDTO.setBirthDate(LocalDate.of(1992, 4, 19));
		patientDTO.setAddress("345 Valid Road");
		patientDTO.setPhoneNumber("5461239834");
		
		visitDTO = new VisitDTO();
		visitDTO.setPatientId(8L);
		visitDTO.setDoctorId(3L);
		visitDTO.setVisitDate(LocalDate.of(2021, 10, 24));
		visitDTO.setVisitTime(LocalTime.of(12, 0));
	}
	
	@Test
	public void shouldReturnListOfPatientsWhenSendingGetRequest() throws Exception {
		// given
		when(patientFacade.getPatients()).thenReturn(List.of(patientDTO));
		
		// when
		mockMvc.perform(get("/patients"))
			
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].firstName", Matchers.is("Mike")));
	}
	
	@Test
	public void shouldReturn201WhenSendingValidPatientPostRequest() throws Exception {
		// given
		when(patientFacade.registerPatient(any())).thenReturn(patientDTO);
		
		// when
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validPatientJsonString))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", Matchers.is("Mike")));
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidPatientPostRequest() throws Exception {
		// given
		when(patientFacade.registerPatient(any())).thenReturn(patientDTO);
		
		// when
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidPatientJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", Matchers.hasSize(1)))
		.andExpect(jsonPath("$.message", Matchers.is("Invalid data format")));
	}
	
	@Test
	public void shouldReturn400WhenSendingNotReadableJsonPostRequest() throws Exception {
		// given
		when(patientFacade.registerPatient(any())).thenReturn(patientDTO);
		
		// when
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(notReadableDateJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", Matchers.is("Malformed JSON request")));
	}
	
	@Test
	public void shouldReturnPatientWhenSendingGetRequestWithId() throws Exception {
		// given
		when(patientFacade.getPatientById(1L)).thenReturn(patientDTO);	
		
		// when
		mockMvc.perform(get("/patients/{id}", 1L))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	public void shouldReturnListOfPatientsByLastnameWhenSendingGetRequestWithParameter() throws Exception {
		// given
		when(patientFacade.getPatientsByLastName(any())).thenReturn(List.of(patientDTO));
		
		// when
		mockMvc.perform(get("/patients")
				.param("lastname", "value"))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].firstName", Matchers.is("Mike")));
	}
	
	@Test
	public void shouldReturn404WhenSendingGetRequestAndPatientDoesNotExist() throws Exception {
		// given
		when(patientFacade.getPatientById(any())).thenAnswer(invocation -> { 
			throw new PatientNotFoundException("Patient with id: " + invocation.getArgument(0) + " does not exist"); 
		});
		
		// when
		mockMvc.perform(get("/patients/{id}", 2L))
		
		// then
		.andExpect(status().isNotFound())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", Matchers.is("Patient with id: 2 does not exist")));
	}
	
	@Test
	public void shouldReturn200WhenSendingUpdatePatientPutRequest() throws Exception {
		// given
		when(patientFacade.updatePatient(any())).thenReturn(patientDTO);
		
		// when
		mockMvc.perform(put("/patients/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(validPatientJsonString))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", Matchers.is("Mike")));
	}
	
	@Test
	public void shouldReturn201WhenSendingValidVisitPostRequest() throws Exception {
		// given
		when(patientFacade.addVisit(any())).thenReturn(visitDTO);
		
		// when
		mockMvc.perform(post("/patients/8/visits")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validVisitJsonString))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.patientId", Matchers.is(8)));
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidVisitPostRequest() throws Exception {
		// given
		when(patientFacade.addVisit(any())).thenReturn(visitDTO);
		
		// when
		mockMvc.perform(post("/patients/8/visits")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidVisitJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", Matchers.hasSize(1)))
		.andExpect(jsonPath("$.message", Matchers.is("Invalid data format")));
	}
}
