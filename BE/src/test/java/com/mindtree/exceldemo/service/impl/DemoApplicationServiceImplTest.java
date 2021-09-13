package com.mindtree.exceldemo.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.exceldemo.entity.Department;
import com.mindtree.exceldemo.entity.Employee;
import com.mindtree.exceldemo.exception.service.custom.DepartmentNotPresentException;
import com.mindtree.exceldemo.exception.service.custom.EmployeeNotPresentException;
import com.mindtree.exceldemo.exception.service.custom.NoDepartmentDataError;
import com.mindtree.exceldemo.repository.DepartmentRepository;
import com.mindtree.exceldemo.repository.EmployeeRepository;
import com.mindtree.exceldemo.service.DemoApplicationService;

@RunWith(SpringRunner.class)
public class DemoApplicationServiceImplTest {

	private XSSFWorkbook workbook;

	@TestConfiguration
	static class TestConfig {

		@Bean
		public DemoApplicationService getServiceBean() {
			return new DemoApplicationServiceImpl();
		}
	}

	@Autowired
	private DemoApplicationService service;

	@MockBean
	private DepartmentRepository deptRepository;

	@MockBean
	private EmployeeRepository empRepository;

	@Before
	public void getDemoExcel() {

//		Create Mock Excel With Dummy Data
		workbook = new XSSFWorkbook();
//		Employee Sheet 
		XSSFSheet empSheet = workbook.createSheet("Employee");
		{
			Row row = empSheet.createRow(0);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue("ID");
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("NAME");
		}
		{
			Row row = empSheet.createRow(1);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(1);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("John");
		}
		{
			Row row = empSheet.createRow(2);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(2);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("Winston");
		}

//		EMP_DEP Sheet
		XSSFSheet empDepSheet = workbook.createSheet("EMP_D");
		{
			Row row = empDepSheet.createRow(0);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue("ID");
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("EMP_ID");
			Cell cell3 = row.createCell(2);
			cell3.setCellValue("DEP_ID");
		}
		{
			Row row = empDepSheet.createRow(1);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(1);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(1);
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(101);
		}
		{
			Row row = empDepSheet.createRow(2);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(2);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(2);
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(102);
		}

//		Dept Sheet
		XSSFSheet depSheet = workbook.createSheet("Department");
		{
			Row row = depSheet.createRow(0);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue("ID");
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("NAME");
		}
		{
			Row row = depSheet.createRow(1);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(101);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("TTH");
		}
		{
			Row row = depSheet.createRow(2);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(102);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("BFSI");
		}
	}

	@Test
	public void testGetAllDepartmentData() {

		List<Department> dept = new ArrayList<>();

		List<Employee> emp1 = new ArrayList<>();
		emp1.add(new Employee(1, "A"));
		emp1.add(new Employee(2, "B"));

		List<Employee> emp2 = new ArrayList<>();
		emp1.add(new Employee(3, "X"));
		emp1.add(new Employee(4, "Y"));

		dept.add(new Department(1, "TTH", emp1));
		dept.add(new Department(2, "RCM", emp2));

		Mockito.when(deptRepository.findAll()).thenReturn(dept);

		assertEquals(dept, service.getAllDepartmentData());

	}

	@Test(expected = NoDepartmentDataError.class)
	public void testNoDepartmentsPresent() throws NoDepartmentDataError {
		Mockito.when(deptRepository.findAll()).thenReturn(Collections.emptyList());
		service.getAllDepartmentData();
	}

	@Test
	public void testInsertDataIntoDB() {

		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenAnswer(i -> i.getArgument(0));
		Mockito.when(deptRepository.save(Mockito.any(Department.class))).thenAnswer(i -> i.getArgument(0));

		Mockito.when(empRepository.findById((long) 1)).thenReturn(Optional.of(new Employee(1, "John")));
		Mockito.when(empRepository.findById((long) 2)).thenReturn(Optional.of(new Employee(1, "Winston")));
		Mockito.when(deptRepository.findById((long) 101)).thenReturn(Optional.of(new Department(101, "TTH", null)));
		Mockito.when(deptRepository.findById((long) 102)).thenReturn(Optional.of(new Department(102, "BFSI", null)));

		File file = new File("Demo.xlsx");
		MultipartFile mPartFile = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			FileInputStream fis = new FileInputStream(file);
			mPartFile = new MockMultipartFile("Demo.xlsx", fis);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Data Insertion Complete", service.insertDataIntoDB(mPartFile));

	}

	@Test(expected = DepartmentNotPresentException.class)
	public void testNoDepartmentInWorkSheet() {

		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenAnswer(i -> i.getArgument(0));
		Mockito.when(deptRepository.save(Mockito.any(Department.class))).thenAnswer(i -> i.getArgument(0));

		Mockito.when(empRepository.findById((long) 1)).thenReturn(Optional.of(new Employee(1, "John")));
		Mockito.when(empRepository.findById((long) 2)).thenReturn(Optional.of(new Employee(1, "Winston")));
		Mockito.when(deptRepository.findById((long) 101)).thenReturn(Optional.empty());
		Mockito.when(deptRepository.findById((long) 102)).thenReturn(Optional.of(new Department(102, "BFSI", null)));

		File file = new File("Demo.xlsx");
		MultipartFile mPartFile = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			FileInputStream fis = new FileInputStream(file);
			mPartFile = new MockMultipartFile("Demo.xlsx", fis);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.insertDataIntoDB(mPartFile);
	}

	@Test(expected = EmployeeNotPresentException.class)
	public void testNoEmployeeInWorkSheet() {

		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenAnswer(i -> i.getArgument(0));
		Mockito.when(deptRepository.save(Mockito.any(Department.class))).thenAnswer(i -> i.getArgument(0));

		Mockito.when(empRepository.findById((long) 1)).thenReturn(Optional.empty());
		Mockito.when(empRepository.findById((long) 2)).thenReturn(Optional.of(new Employee(1, "Winston")));
		Mockito.when(deptRepository.findById((long) 101)).thenReturn(Optional.of(new Department(101, "TTH", null)));
		Mockito.when(deptRepository.findById((long) 102)).thenReturn(Optional.of(new Department(102, "BFSI", null)));

		File file = new File("Demo.xlsx");
		MultipartFile mPartFile = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			FileInputStream fis = new FileInputStream(file);
			mPartFile = new MockMultipartFile("Demo.xlsx", fis);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		service.insertDataIntoDB(mPartFile);
	}
}
