package com.healthcare.rest.controller;

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

import com.healthcare.rest.security.SecurityConfig;
import com.healthcare.service.DoctorService;
import com.healthcare.service.VisitService;
import com.healthcare.service.dto.DoctorDTO;
import com.healthcare.service.dto.VisitDTO;
import com.healthcare.service.exception.DoctorNotFoundException;

@WebMvcTest(
		controllers = DoctorApi.class, 
		excludeAutoConfiguration = SecurityAutoConfiguration.class, 
		excludeFilters = @ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE, 
			classes = { SecurityConfig.class, LoginApi.class }
		) 
	)
public class DoctorApiTest {
	
	private final String validDoctorJsonString = "{"
			+ "\"firstName\":\"Greg\","
			+ "\"lastName\":\"Monty\","
			+ "\"birthDate\":\"1987-03-12\","
			+ "\"address\":\"345 Valid Route\","
			+ "\"phoneNumber\":\"5461239834\","
			+ "\"email\":null,"
			+ "\"specialty\":\"dermatolody\"}";
	
	private final String invalidDoctorJsonString = "{"
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
	
	private static DoctorDTO doctorDTO;
	
	private static VisitDTO visitDTO;
		
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DoctorService doctorService;
	
	@MockBean
	private VisitService visitService;
	
	@BeforeAll
	public static void init() {	
		doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setFirstName("Greg");
		doctorDTO.setLastName("Monty");
		doctorDTO.setBirthDate(LocalDate.of(1987, 3, 12));
		doctorDTO.setAddress("345 Valid Route");
		doctorDTO.setPhoneNumber("5461239834");
		
		visitDTO = new VisitDTO();
		visitDTO.setPatientId(8L);
		visitDTO.setDoctorId(3L);
		visitDTO.setVisitDate(LocalDate.of(2021, 10, 24));
		visitDTO.setVisitTime(LocalTime.of(12, 0));
	}
	
	@Test
	public void shouldReturnListOfDoctorsWhenSendingGetRequest() throws Exception {
		// given
		when(doctorService.getDoctors()).thenReturn(List.of(doctorDTO));
		
		// when
		mockMvc.perform(get("/doctors"))
			
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].firstName", Matchers.is("Greg")));
	}
	
	@Test
	public void shouldReturn201WhenSendingValidPostRequest() throws Exception {
		// given
		when(doctorService.addDoctor(any())).thenReturn(doctorDTO);
		
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validDoctorJsonString))
		
		// then
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", Matchers.is("Greg")));
	}
	
	@Test
	public void shouldReturn400WhenSendingInvalidPostRequest() throws Exception {
		// given
		when(doctorService.addDoctor(any())).thenReturn(doctorDTO);
		
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidDoctorJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.fieldErrors", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.message", Matchers.is("Invalid data format")));
	}
	
	@Test
	public void shouldReturn400WhenSendingNotReadableJsonPostRequest() throws Exception {
		// given
		when(doctorService.addDoctor(any())).thenReturn(doctorDTO);
		
		// when
		mockMvc.perform(post("/doctors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(notReadableDateJsonString))
		
		// then
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", Matchers.is("Malformed JSON request")));
	}
	
	@Test
	public void shouldReturnDoctorWhenSendingGetRequestWithId() throws Exception {
		// given
		when(doctorService.getDoctorById(1L)).thenReturn(doctorDTO);	
		
		// when
		mockMvc.perform(get("/doctors/{id}", 1L))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	public void shouldReturnListOfDoctorsByLastnameWhenSendingGetRequestWithParameter() throws Exception {
		// given
		when(doctorService.getDoctorsByLastName(any())).thenReturn(List.of(doctorDTO));
		
		// when
		mockMvc.perform(get("/doctors")
				.param("lastname", "value"))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].firstName", Matchers.is("Greg")));
	}
	
	@Test
	public void shouldReturn404WhenSendingGetRequestAndDoctorDoesNotExist() throws Exception {
		// given
		when(doctorService.getDoctorById(2L)).thenAnswer(invocation -> {
			throw new DoctorNotFoundException("Doctor with id: " + invocation.getArgument(0) + " does not exist");
		});
		
		// when
		mockMvc.perform(get("/doctors/{id}", 2L))
		
		// then
		.andExpect(status().isNotFound())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", Matchers.is("Doctor with id: 2 does not exist")));
	}
	
	@Test
	public void shouldReturn200WhenSendingPutRequest() throws Exception {
		// given
		when(doctorService.updateDoctor(any(), any())).thenReturn(doctorDTO);
		
		// when
		mockMvc.perform(put("/doctors/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(validDoctorJsonString))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.firstName", Matchers.is("Greg")));
	}
	
	@Test
	public void shouldReturnListOfDoctorsVisitsWhenSendingGetRequestWithParameters() throws Exception {
		// given
		when(visitService.getVisitsByDoctorIdBetweenDates(any(), any(), any())).thenReturn(List.of(visitDTO));
		
		// when
		mockMvc.perform(get("/doctors/3/visits")
				.param("startDate", "2019-10-12")
				.param("endDate", "2020-02-17"))
		
		// then
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].visitDate", Matchers.is("2021-10-24")));
	}
	
	@Test
	public void shouldReturn400WhenSendingGetRequestWithInvalidParameters() throws Exception {
		// given
		when(visitService.getVisitsByDoctorIdBetweenDates(any(), any(), any())).thenReturn(List.of(visitDTO));
		
		// when
		mockMvc.perform(get("/doctors/3/visits")
				.param("startDate", "2019-1-12")
				.param("endDate", "2020-02-1"))
		
		// then
		.andExpect(status().isBadRequest());
	}
}