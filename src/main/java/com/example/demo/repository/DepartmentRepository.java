package com.example.demo.repository;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
	@Query(value = "select * from departments d where d.company_id = :companyId",nativeQuery = true)
	List<DepartmentEntity> findAllByCompanyId(@Param("companyId") String companyId);
};

