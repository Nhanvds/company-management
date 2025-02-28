package com.example.demo.service;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.exception.CompanyNotFoundException;

import java.util.List;


public interface CompanyService {

	CompanyDTO saveCompany(CompanyDTO companyDTO);

	List<CompanyDTO> getAllCompanies();

	CompanyDTO getCompanyById(String id) throws CompanyNotFoundException;

	CompanyDTO updateCompany(String id, CompanyDTO company) throws CompanyNotFoundException;

	void deleteCompany(String id) throws CompanyNotFoundException;

}