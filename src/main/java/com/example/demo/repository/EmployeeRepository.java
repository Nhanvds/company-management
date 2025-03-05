package com.example.demo.repository;

import com.example.demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
	// alias employee as e
	@Query(value = "select * from employees e where e.company_id = :companyId",nativeQuery = true)
	List<EmployeeEntity> findAllByCompanyId(@Param("companyId") String companyId);
	@Query(value = "select * from employees e where e.department_id = :departmentId",nativeQuery = true)
	List<EmployeeEntity> findAllByDepartmentId(@Param("departmentId") String departmentId);
};