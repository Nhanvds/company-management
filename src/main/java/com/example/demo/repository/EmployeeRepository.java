package com.example.demo.repository;

import com.example.demo.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
	// alias employee as e
	@Query(value = "select * from employees e where e.company_id = :companyId", nativeQuery = true)
	List<EmployeeEntity> findAllByCompanyId(@Param("companyId") String companyId);

	@Query(value = "select * from employees e where e.department_id = :departmentId", nativeQuery = true)
	List<EmployeeEntity> findAllByDepartmentId(@Param("departmentId") String departmentId);

	//  chít chít
	// 8-3 vui vẻ
	@Query(value = """
       SELECT * FROM employees e 
       WHERE 
           (:keyword IS NULL OR :keyword = '' OR 
            LOWER(e.employee_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR 
            LOWER(e.employee_email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR 
            LOWER(e.employee_phone_number) LIKE LOWER(CONCAT('%', :keyword, '%')))
       """, nativeQuery = true)
	Page<EmployeeEntity> searchEmployee(Pageable pageable, @Param("keyword") String keyword);

};