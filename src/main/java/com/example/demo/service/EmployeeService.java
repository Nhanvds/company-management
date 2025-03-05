package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws CompanyNotFoundException,DepartmentNotFoundException;
	// EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws CompanyNotFoundException, DepartmentNotFoundException;
	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException;

	EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) throws EmployeeNotFoundException, CompanyNotFoundException,DepartmentNotFoundException; // ket hop giua save va get
	// EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) throws EmployeeNotFoundException, CompanyNotFoundException, DepartmentNotFoundException;
	void deleteEmployee(String id) throws EmployeeNotFoundException; // void ko can tra ve
	List<EmployeeDTO> getAllEmployeesByCompanyId(String companyId) throws CompanyNotFoundException;
	List<EmployeeDTO> getAllEmployeesByDepartmentId(String departmentId) throws DepartmentNotFoundException;
}
