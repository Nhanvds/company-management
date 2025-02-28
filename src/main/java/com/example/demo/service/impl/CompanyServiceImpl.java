package com.example.demo.service.impl;
import com.example.demo.component.CompanyMapper;
import com.example.demo.component.DepartmentMapper;
import com.example.demo.component.EmployeeMapper;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final CompanyMapper companyMapper;
	private final EmployeeMapper employeeMapper;
	private final DepartmentMapper departmentMapper;

	public CompanyServiceImpl (CompanyRepository companyRepository,
	                           EmployeeRepository employeeRepository,
	                           DepartmentRepository departmentRepository,
	                           EmployeeMapper employeeMapper,
	                           CompanyMapper companyMapper,
	                           DepartmentMapper departmentMapper) {
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
		this.departmentRepository=departmentRepository;
		this.companyMapper=companyMapper;
		this.employeeMapper=employeeMapper;
		this.departmentMapper=departmentMapper;
	}

	@Override
	public CompanyDTO saveCompany(CompanyDTO companyDTO) {
		CompanyEntity companyEntity = companyMapper.toEntity(companyDTO);
		companyRepository.save(companyEntity);
		return companyMapper.toDTO(companyEntity);
	}
	@Override
	public List<CompanyDTO> getAllCompanies() {
		List<CompanyEntity> companyEntities = companyRepository.findAll();

		return companyEntities.stream()
				.map(companyMapper::toDTO) // .map(e -> companyMapper.toDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public CompanyDTO getCompanyById(String id) throws CompanyNotFoundException {
//---------------------------------------------
	Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
		if(companyEntity.isPresent()) {
		}
		return null;
	}

	@Override
	public CompanyEntity updateCompany(String id, CompanyEntity company) {
		Optional<CompanyEntity> companyEntity= companyRepository.findById(id);
		if(companyEntity.isPresent()) {
			CompanyEntity newCompany= companyEntity.get();
			newCompany.setCompanyName(company.getCompanyName());
			newCompany.setCompanyAddress(company.getCompanyAddress());
			companyRepository.save(newCompany); // lưu vào db
			return newCompany;
		}
		return null;
	}

	@Override
	public void deleteCompany(String id) {
		Optional<CompanyEntity> companyEntity= companyRepository.findById(id);
		if(companyEntity.isPresent()) {
			companyRepository.deleteById(id);
		}
	}
}
