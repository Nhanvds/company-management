package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
	DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws CompanyNotFoundException;
	List<DepartmentDTO> getAllDepartments();
	DepartmentDTO getDepartmentById(String id) throws DepartmentNotFoundException;
	DepartmentDTO updateDepartment(String id, DepartmentDTO departmentDTO) throws DepartmentNotFoundException,CompanyNotFoundException;
	void deleteDepartment(String id) throws DepartmentNotFoundException;
	List<DepartmentDTO> getAllDepartmentsByCompanyId(String companyId) throws CompanyNotFoundException;
}
