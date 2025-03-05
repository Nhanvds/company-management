package com.example.demo.component;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component

public class EmployeeMapper {
	public void updateEntity(EmployeeDTO dto, EmployeeEntity entity) {
		if ( dto == null || entity == null) {
			return;
		}
		entity.setEmployeeName(dto.getEmployeeFullName());
		entity.setEmployeeEmail(dto.getEmployeeEmail());
		entity.setEmployeePhoneNumber(dto.getEmployeePhoneNumber());
		entity.setEmployeePosition(dto.getEmployeePosition());
		entity.setCreatedAt(dto.getCreateAt());
	}
	public EmployeeEntity toEntity(EmployeeDTO dto) {
		if(dto==null) {
			return null;
		}
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmployeePosition(dto.getEmployeePosition());
		entity.setEmployeeEmail(dto.getEmployeeEmail());
		entity.setEmployeeName(dto.getEmployeeFullName());
		entity.setEmployeePhoneNumber(dto.getEmployeePhoneNumber());
		entity.setCreatedAt(dto.getCreateAt());

		return entity;
	}
	public EmployeeDTO toDTO(EmployeeEntity entity) {
		if(entity==null) {
			return null;
		}
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setEmployeePhoneNumber(entity.getEmployeePhoneNumber());
		dto.setEmployeeEmail(entity.getEmployeeEmail());
		dto.setEmployeeFullName(entity.getEmployeeName());
		dto.setEmployeePosition(entity.getEmployeePosition());
		dto.setCreatedAt(entity.getCreatedAt());

		return dto;
	}
}
