package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.service.impl.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
	private final DepartmentServiceImpl departmentService;

	public DepartmentController(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}

	;

	@PostMapping("/save")
	public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) throws CompanyNotFoundException {
		return new ResponseEntity<>(departmentService.saveDepartment(departmentDTO), HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<DepartmentDTO> getDetailEmployee(@PathVariable("id") String id) throws DepartmentNotFoundException {
		return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id") String id, @RequestBody DepartmentDTO departmentDTO) throws CompanyNotFoundException, DepartmentNotFoundException, EmployeeNotFoundException {
		return new ResponseEntity<>(departmentService.updateDepartment(id, departmentDTO), HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") String id) throws DepartmentNotFoundException {
		return new ResponseEntity<>("delete successfully!", HttpStatus.OK);
	}

	@GetMapping("/{company_id}/get-all-departments")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartmentsByCompanyId(@PathVariable("company_id") String companyId) throws CompanyNotFoundException {
		return new ResponseEntity<>(departmentService.getAllDepartmentsByCompanyId(companyId),HttpStatus.OK);
	}
}

