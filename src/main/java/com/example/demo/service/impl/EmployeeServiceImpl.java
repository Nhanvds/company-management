package com.example.demo.service.impl;

import com.example.demo.component.CompanyMapper;
import com.example.demo.component.DepartmentMapper;
import com.example.demo.component.EmployeeMapper;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.exception.CompanyNotFoundException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final CompanyRepository companyRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeMapper employeeMapper;
	private final CompanyMapper companyMapper;
	private final DepartmentMapper departmentMapper;

	public  EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository,DepartmentRepository departmentRepository,EmployeeMapper employeeMapper, CompanyMapper companyMapper, DepartmentMapper departmentMapper ){
		this.employeeRepository= employeeRepository;
		this.companyRepository = companyRepository;
		this.departmentRepository = departmentRepository;
		this.employeeMapper = employeeMapper;
		this.departmentMapper = departmentMapper;
		this.companyMapper = companyMapper;
	}

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws CompanyNotFoundException, DepartmentNotFoundException {
		EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeDTO);
		CompanyEntity companyEntity = companyRepository.findById(employeeDTO.getCompanyDTO().getId())
				.orElseThrow(() ->new CompanyNotFoundException("Company not found" ));
		employeeEntity.setCompany(companyEntity);
		DepartmentEntity departmentEntity = departmentRepository.findById(employeeDTO.getDepartmentDTO().getId())
				.orElseThrow(()-> new DepartmentNotFoundException("Department not found"));
		employeeEntity.setDepartment(departmentEntity);
		employeeRepository.save(employeeEntity);
		return employeeMapper.toDTO(employeeEntity);
	}


	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
		return employeeEntities.stream()
				.map(employeeMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException {
		EmployeeEntity employeeEntity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		EmployeeDTO employeeDTO = employeeMapper.toDTO(employeeEntity);
		employeeDTO.setCompanyDTO(companyMapper.toDTO(employeeEntity.getCompany()));
		employeeDTO.setDepartmentDTO(departmentMapper.toDTO(employeeEntity.getDepartment()));
		return employeeDTO;

	}
	@Override
	public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) throws EmployeeNotFoundException, CompanyNotFoundException, DepartmentNotFoundException {
		EmployeeEntity employeeEntity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		employeeMapper.updateEntity(employeeDTO,employeeEntity);

		CompanyEntity companyEntity = companyRepository.findById(employeeDTO.getCompanyDTO().getId())
				.orElseThrow(() -> new CompanyNotFoundException("Company not found"));
		employeeEntity.setCompany(companyEntity);

		DepartmentEntity departmentEntity = departmentRepository.findById(employeeDTO.getDepartmentDTO().getId())
						.orElseThrow(()->new DepartmentNotFoundException("Department not found"));
		employeeEntity.setDepartment(departmentEntity);

		employeeRepository.save(employeeEntity);
		return employeeMapper.toDTO(employeeEntity);
	}

//---------------------
	@Override
	public void deleteEmployee(String id) throws EmployeeNotFoundException {
		EmployeeEntity employeeEntity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		employeeRepository.delete(employeeEntity);
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesByCompanyId(String companyId) throws CompanyNotFoundException {
		CompanyEntity companyEntity = companyRepository.findById(companyId)
				.orElseThrow(() -> new CompanyNotFoundException("Company not found"));
		return employeeRepository.findAllByCompanyId(companyEntity.getId())
				.stream()
				.map(employeeMapper::toDTO)
				.collect(Collectors.toList());

	}
	public List<EmployeeDTO> getAllEmployeesByDepartmentId(String departmentId) throws DepartmentNotFoundException {
		DepartmentEntity departmentEntity = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
		return employeeRepository.findAllByDepartmentId(departmentEntity.getId())
				.stream()
				.map(employeeMapper::toDTO)
				.collect(Collectors.toList());

	}

}
