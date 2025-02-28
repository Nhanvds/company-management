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
		entity.setEmployeeName(dto.getFullName());
		entity.setEmployeeEmail(dto.getEmail());
		entity.setEmployeePhoneNumber(dto.getPhoneNumber());
		entity.setEmployeePosition(dto.getPosition());
		entity.setCreateAt(dto.getCreateAt());
	}
	public EmployeeEntity toEntity(EmployeeDTO dto) {
		if(dto==null) {
			return null;
		}
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmployeePosition(dto.getPosition());
		entity.setEmployeeEmail(dto.getEmail());
		entity.setEmployeeName(dto.getFullName());
		entity.setEmployeePhoneNumber(dto.getPhoneNumber());
		entity.setCreateAt(dto.getCreateAt());

		return entity;
	}
	public EmployeeDTO toDTO(EmployeeEntity entity) {
		if(entity==null) {
			return null;
		}
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getEmployeeId());
		dto.setPhoneNumber(entity.getEmployeePhoneNumber());
		dto.setEmail(entity.getEmployeeEmail());
		dto.setFullName(entity.getEmployeeName());
		dto.setPosition(entity.getEmployeePosition());
		dto.setCreateAt(entity.getCreateAt());

		return dto;
	}
}
