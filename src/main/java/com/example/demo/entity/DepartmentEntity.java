package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

	@Column(name = "department_name", unique = true, nullable = false)
	private String departmentName;

	//ref
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EmployeeEntity> employees;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private CompanyEntity company;

	//------------

	// getters & setters



	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public CompanyEntity getCompanyEntity() {
		return company;
	}

	public void setCompanyEntity(CompanyEntity company) {
		this.company = company;
	}

}
