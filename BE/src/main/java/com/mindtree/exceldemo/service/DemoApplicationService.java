package com.mindtree.exceldemo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.exceldemo.entity.Department;
import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public interface DemoApplicationService {

	List<Department> getAllDepartmentData() throws DemoApplicatonServiceException;

	String insertDataIntoDB(MultipartFile file) throws DemoApplicatonServiceException;

}