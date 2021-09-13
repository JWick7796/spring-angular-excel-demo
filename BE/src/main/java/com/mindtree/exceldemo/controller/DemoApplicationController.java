package com.mindtree.exceldemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.exceldemo.dto.ResponseDTO;
import com.mindtree.exceldemo.entity.Department;
import com.mindtree.exceldemo.service.DemoApplicationService;

@RestController
@CrossOrigin
public class DemoApplicationController {

	@Autowired
	private DemoApplicationService service;

	@GetMapping("/departments")
	public ResponseEntity<ResponseDTO<List<Department>>> getAllData() {
		return new ResponseEntity<ResponseDTO<List<Department>>>(
				new ResponseDTO<List<Department>>(service.getAllDepartmentData(), null,
						"Fetched All Departments With Their Employees", true),
				HttpStatus.OK);
	}

	@PostMapping("/departments")
	public ResponseEntity<ResponseDTO<String>> insertData(@RequestBody MultipartFile file) {
		System.out.println(file);
		return new ResponseEntity<ResponseDTO<String>>(
				new ResponseDTO<String>(service.insertDataIntoDB(file), null, "Data Inserted Successfully", true),
				HttpStatus.OK);
	}

}
