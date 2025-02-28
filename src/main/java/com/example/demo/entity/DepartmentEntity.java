package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "departments")
public class DepartmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String departmentId;

	@Column(name = "name", unique = true, nullable = false)
	private String departmentName;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createAt;

	//ref
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EmployeeEntity> employees;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private CompanyEntity company;

	//------------

	// getters & setters


	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
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
