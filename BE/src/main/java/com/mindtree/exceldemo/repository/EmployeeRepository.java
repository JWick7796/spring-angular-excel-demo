package com.mindtree.exceldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.exceldemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
