package com.mindtree.exceldemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.exceldemo.entity.Department;
import com.mindtree.exceldemo.entity.Employee;
import com.mindtree.exceldemo.service.DemoApplicationService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(DemoApplicationController.class)
public class DemoApplicationControllerTest {

	@InjectMocks
	private DemoApplicationController controller;

	@Mock
	private DemoApplicationService service;

	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetAllData() throws Exception {

		List<Department> dept = new ArrayList<>();

		List<Employee> emp1 = new ArrayList<>();
		emp1.add(new Employee(1, "A"));
		emp1.add(new Employee(2, "B"));
		List<Employee> emp2 = new ArrayList<>();
		emp1.add(new Employee(3, "X"));
		emp1.add(new Employee(4, "Y"));
		dept.add(new Department(1, "TTH", emp1));
		dept.add(new Department(2, "RCM", emp2));

		Mockito.when(service.getAllDepartmentData()).thenReturn(dept);

		mockMvc.perform(get("/departments").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dept))).andExpect(status().isOk());
	}

	@Test
	public void testInsertData() throws Exception {
		MockMultipartFile mPartFile = null;
		byte[] data = null;
		mPartFile = new MockMultipartFile("Demo.xlsx", data);
		mockMvc.perform(MockMvcRequestBuilders.multipart("/departments").file(mPartFile)).andExpect(status().isOk());
	}

}
