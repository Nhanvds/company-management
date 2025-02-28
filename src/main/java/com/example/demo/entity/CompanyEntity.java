package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
public class CompanyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String companyId;

	@Column(name = "name", unique = true, nullable = false)
	private String companyName;
	@Column(name = "company_address", nullable = false)
	private String companyAddress;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createAt;

	//ref
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<EmployeeEntity> employees;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<DepartmentEntity> departments;
	//-----------------
	//getters & setters

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
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
