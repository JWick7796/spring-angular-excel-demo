package com.mindtree.exceldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.exceldemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
