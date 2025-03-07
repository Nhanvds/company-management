package com.example.demo.service.impl;

import com.example.demo.component.CompanyMapper;
import com.example.demo.component.DepartmentMapper;
import com.example.demo.component.EmployeeMapper;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DepartmentService;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final EmployeeRepository employeeRepository;
	private final CompanyRepository companyRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeMapper employeeMapper;
	private final CompanyMapper companyMapper;
	private final DepartmentMapper departmentMapper;

	public DepartmentServiceImpl(EmployeeRepository employeeRepository,
	                             CompanyRepository companyRepository,
	                             DepartmentRepository departmentRepository,
	                             EmployeeMapper employeeMapper,
	                             CompanyMapper companyMapper,
	                             DepartmentMapper departmentMapper) {
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
		this.employeeMapper = employeeMapper;
		this.companyMapper = companyMapper;
		this.departmentMapper = departmentMapper;
	}

	@Override
	public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws CompanyNotFoundException {
		DepartmentEntity departmentEntity = departmentMapper.toEntity(departmentDTO);
		CompanyEntity companyEntity = companyRepository.findById(
				departmentDTO.getCompanyDTO().getId())
				.orElseThrow(()->new CompanyNotFoundException(
						"Company not found"));
		departmentEntity.setCompanyEntity(companyEntity);
		departmentRepository.save(departmentEntity);
		return departmentMapper.toDTO(departmentEntity);
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {
		List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
		return departmentEntities.stream()
				.map(departmentMapper::toDTO)
				.collect(Collectors.toList());
	}
	@Override
	public DepartmentDTO getDepartmentById(String id) throws DepartmentNotFoundException {
		DepartmentEntity departmentEntity= departmentRepository.findById(id)
				.orElseThrow(()->new DepartmentNotFoundException("Department not found"));
		DepartmentDTO departmentDTO = departmentMapper.toDTO(departmentEntity);
		departmentDTO.setCompanyDTO(companyMapper.toDTO(departmentEntity.getCompanyEntity()));
				return departmentDTO;
	}

//----------------------
	@Override
	public DepartmentDTO updateDepartment(String id, DepartmentDTO departmentDTO) throws DepartmentNotFoundException, CompanyNotFoundException,EmployeeNotFoundException {
	DepartmentEntity departmentEntity = departmentRepository.findById(id)
			.orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
	departmentMapper.updateEntity(departmentDTO,departmentEntity);
	CompanyEntity companyEntity = companyRepository.findById(departmentDTO.getCompanyDTO().getId())
			.orElseThrow(() ->new CompanyNotFoundException("Company not found"));
	departmentEntity.setCompanyEntity(companyEntity);
	departmentRepository.save(departmentEntity);
	return departmentMapper.toDTO(departmentEntity);
	}

	@Override
	public  void deleteDepartment(String id) throws DepartmentNotFoundException {
		DepartmentEntity departmentEntity = departmentRepository.findById(id)
				.orElseThrow(()-> new DepartmentNotFoundException("Department not found"));
		departmentRepository.delete(departmentEntity);
	}

	@Override
	public List<DepartmentDTO> getAllDepartmentsByCompanyId(String companyId) throws CompanyNotFoundException {
		CompanyEntity companyEntity = companyRepository.findById(companyId)
				.orElseThrow(()->new CompanyNotFoundException("Company not found"));
		return departmentRepository.findAllByCompanyId(companyEntity.getId())
				.stream()
				.map(departmentMapper::toDTO)
				.collect(Collectors.toList());
	}


}
