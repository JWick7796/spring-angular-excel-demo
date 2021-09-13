package com.mindtree.exceldemo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.exceldemo.entity.Department;
import com.mindtree.exceldemo.entity.Employee;
import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;
import com.mindtree.exceldemo.exception.service.custom.DepartmentNotPresentException;
import com.mindtree.exceldemo.exception.service.custom.EmployeeNotPresentException;
import com.mindtree.exceldemo.exception.service.custom.FileReadException;
import com.mindtree.exceldemo.exception.service.custom.NoDepartmentDataError;
import com.mindtree.exceldemo.repository.DepartmentRepository;
import com.mindtree.exceldemo.repository.EmployeeRepository;
import com.mindtree.exceldemo.service.DemoApplicationService;

@Service
public class DemoApplicationServiceImpl implements DemoApplicationService {

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public List<Department> getAllDepartmentData() throws DemoApplicatonServiceException {
		List<Department> departmentData = deptRepository.findAll();
		if (departmentData.isEmpty()) {
			throw new NoDepartmentDataError("No Departments Present");
		}
		return departmentData;
	}

	@Override
	public String insertDataIntoDB(MultipartFile file) throws DemoApplicatonServiceException {
		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
		} catch (IOException e) {
			throw new FileReadException("Error Processing File");
		}

//		Read Employee Sheet
		XSSFSheet employeeSheet = workbook.getSheetAt(0);
		for (int i = 1; i < employeeSheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = employeeSheet.getRow(i);
			Employee employee = new Employee();
			employee.setId((long) row.getCell(0).getNumericCellValue());
			employee.setName(row.getCell(1).getStringCellValue());

			empRepository.saveAndFlush(employee);
		}

//		Read Department Sheet
		XSSFSheet departmentSheet = workbook.getSheetAt(2);
		for (int i = 1; i < departmentSheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = departmentSheet.getRow(i);
			Department department = new Department();
			department.setId((long) row.getCell(0).getNumericCellValue());
			department.setName(row.getCell(1).getStringCellValue());
			deptRepository.saveAndFlush(department);
		}

//		Add Employees to Department
		XSSFSheet deptEmpRelationSheet = workbook.getSheetAt(1);
		for (int i = 1; i < deptEmpRelationSheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = deptEmpRelationSheet.getRow(i);

//			Get Department
			Department department = deptRepository.findById((long) row.getCell(2).getNumericCellValue())
					.orElseThrow(() -> new DepartmentNotPresentException("Department with ID: "
							+ row.getCell(2).getNumericCellValue() + " does not exists (Sheet: "
							+ deptEmpRelationSheet.getSheetName() + ", Row: " + (row.getRowNum() + 1) + ", Cell: 3)"));

//			Get Employee
			Employee employee = empRepository.findById((long) row.getCell(1).getNumericCellValue())
					.orElseThrow(() -> new EmployeeNotPresentException("Employee with ID: "
							+ row.getCell(1).getNumericCellValue() + " does not exists (Sheet: "
							+ deptEmpRelationSheet.getSheetName() + ", Row: " + (row.getRowNum() + 1) + ", Cell: 2)"));

			if (department.getEmployees() != null) {
				List<Employee> existingDeptEmployees = department.getEmployees();
				existingDeptEmployees.add(employee);
				department.setEmployees(existingDeptEmployees);
			} else {
				List<Employee> employees = new ArrayList<Employee>();
				employees.add(employee);
				department.setEmployees(employees);
			}
			deptRepository.saveAndFlush(department);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			throw new FileReadException("Error Processing File");
		}
		return "Data Insertion Complete";
	}

}
