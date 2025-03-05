package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.service.impl.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	//
	private final CompanyServiceImpl companyService;
	public CompanyController (CompanyServiceImpl companyService) {
		this.companyService=companyService;
	}

	//
	@PostMapping("/save")
	public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {
		return new ResponseEntity<>(companyService.saveCompany(companyDTO), HttpStatus.OK);
	}
	@GetMapping("/detail/{id}")
	public ResponseEntity<CompanyDTO> getDetailCompany(@PathVariable("id") String id) throws CompanyNotFoundException {
		return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
	}
	//-------
	@GetMapping("/get-all")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}
	//-------
	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") String id,@RequestBody CompanyDTO companyDTO) throws CompanyNotFoundException {
		return new ResponseEntity<>(companyService.updateCompany(id, companyDTO),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id") String id) throws CompanyNotFoundException {
		companyService.deleteCompany(id);
		return new ResponseEntity<>("delete successfully!",HttpStatus.OK);
	}
}
