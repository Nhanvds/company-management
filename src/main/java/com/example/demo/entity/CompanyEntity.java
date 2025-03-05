package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {
	@Column(name = "company_name", unique = true, nullable = false)
	private String companyName;
	@Column(name = "company_address", nullable = false)
	private String companyAddress;

	//ref
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL) // mappedBy phai cung ten voi companyEmtity
	private List<EmployeeEntity> employees;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<DepartmentEntity> departments;
	//-----------------
	//getters & setters

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public List<DepartmentEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}
}
