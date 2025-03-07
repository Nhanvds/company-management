package com.example.demo.service.impl;
import com.example.demo.component.CompanyMapper;
import com.example.demo.component.DepartmentMapper;
import com.example.demo.component.EmployeeMapper;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
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

		CompanyEntity companyEntity = companyRepository.findById(id)
				.orElseThrow(() ->new CompanyNotFoundException("Company not found"));
		List<EmployeeEntity> employeeEntities = employeeRepository.findAllByCompanyId(companyEntity.getId());
		CompanyDTO companyDTO = companyMapper.toDTO(companyEntity);
		companyDTO.setEmployees(
				employeeEntities.stream()
						.map(employeeMapper::toDTO)
						.collect(Collectors.toList())

		);
		List<DepartmentEntity> departmentEntities = departmentRepository.findAllByCompanyId(companyEntity.getId());
		companyDTO.setDepartments(
				departmentEntities.stream()
						.map(departmentMapper::toDTO)
						.collect(Collectors.toList())
		);
		return companyDTO;
	}
	//---------------------------------------------
	@Override
	public CompanyDTO updateCompany(String id, CompanyDTO companyDTO) throws CompanyNotFoundException  {
		CompanyEntity companyEntity = companyRepository.findById(id)
				.orElseThrow(() -> new CompanyNotFoundException("Company not found"));
		// map từ công ty mới sang công ty đã tồn tại
		companyMapper.updateEntity(companyDTO,companyEntity);
		// lưu vào trong db
		companyRepository.save(companyEntity);
		// những phương thức giao tiếp với db thông qua repository phải là entity
		return companyMapper.toDTO(companyEntity);
	}

	@Override
	public void deleteCompany(String id) throws CompanyNotFoundException{
		CompanyEntity companyEntity = companyRepository.findById(id)
				.orElseThrow(()->new CompanyNotFoundException("Company not found"));
		companyRepository.delete(companyEntity);
	}

}
