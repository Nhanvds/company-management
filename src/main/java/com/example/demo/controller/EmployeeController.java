package com.example.demo.controller;

import com.example.demo.component.EmployeeMapper;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DataConflictException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.impl.EmployeeServiceImpl;
import com.example.demo.utils.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeServiceImpl employeeService;
	public EmployeeController (EmployeeServiceImpl employeeService) {
		this.employeeService=employeeService;
	}
	@PostMapping("/save")
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws CompanyNotFoundException, DepartmentNotFoundException, DataConflictException {
		return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.OK);
	}
	@GetMapping("/detail/{id}")
	public ResponseEntity<EmployeeDTO> getDetailEmployee(@PathVariable("id") String id) throws EmployeeNotFoundException {
		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	@GetMapping("/get-all")
	public  ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);

	}
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDTO>  updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) throws CompanyNotFoundException, DepartmentNotFoundException, EmployeeNotFoundException, DataConflictException {
		return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDTO), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) throws EmployeeNotFoundException {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Delete successfully!",HttpStatus.OK);
	}
	@GetMapping("/{company_id}/get-all-employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByCompanyId(@PathVariable("company_id") String companyId) throws CompanyNotFoundException{
		return new ResponseEntity<>(employeeService.getAllEmployeesByCompanyId(companyId),HttpStatus.OK);
	}

	@GetMapping("/{department_id}/get-all-employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByDepartmentId(@PathVariable("department_id") String departmentId) throws DepartmentNotFoundException {
		return new ResponseEntity<>(employeeService.getAllEmployeesByDepartmentId(departmentId),HttpStatus.OK);
	}

	// tìm kiếm, phân trang
	@GetMapping("/search")
	public ResponseEntity<PageResponseDTO<EmployeeDTO>> searchEmployee(
			@RequestParam(value = "pageNumber", defaultValue = Constant.PAGE_NUMBER_DEFAULT) Integer pageNumber, // null nếu không có param tên pageNumber
			@RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE_DEFAULT) Integer pageSize,
			@RequestParam(value = "keyword",defaultValue = Constant.KEYWORD) String keyword,
			@RequestParam(value = "sortProperty",defaultValue = Constant.SORT_PROPERTY) String sortProperty,
			@RequestParam(value = "orderBy",defaultValue = Constant.ORDER_BY_DESC) String orderBy
	){
		return new ResponseEntity<PageResponseDTO<EmployeeDTO>>(employeeService.searchEmployee(pageNumber, pageSize, keyword, sortProperty, orderBy),HttpStatus.OK);
	}
}
