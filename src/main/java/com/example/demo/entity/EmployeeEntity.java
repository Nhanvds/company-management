package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

	@Column(name = "employee_name",nullable = false)
	private String employeeName;

	@Column(name = "employee_email", unique = true, nullable = false)
	private String employeeEmail;
	@Column(name = "employee_phone_number", nullable = false)
	private String employeePhoneNumber;
	@Column(name = "employee_position",nullable = false)
	private String employeePosition;

	//ref
	// 1.dùng joincolumn để thiết lập khóa ngoại trong db đặt tên là gì,
	//   khi khai báo kiểu dữ liệu là một entity CompanyEntity thì jpa
	//   sẽ tự liên kết khóa ngoại company_id với khóa chính tương ứng
	//   với bảng của CompanyEntity

	// 2.trong mối quan hệ 1-n ví dụ như của employee-company thì @ManyToOne
	//   là bắt buộc cần thiết lập ở bên Many, còn @OneToMany có thể có hoặc không
	//   ở bên One. ở bên CompanyEntity tôi vẫn thiết lập @OneToMany, và hiện tại nên thiết lập đầy đủ.

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private CompanyEntity company;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private DepartmentEntity department;

	//----------------------------
	//getters & setters


	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}
}
