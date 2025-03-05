package com.example.demo.component;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
	public void updateEntity(DepartmentDTO dto, DepartmentEntity entity) {
		if(dto==null || entity ==null) {
			return;
		}
		entity.setDepartmentName(dto.getDepartmentName());
		entity.setCreatedAt(dto.getCreatedAt());
	}

	public DepartmentEntity toEntity(DepartmentDTO dto){
		if(dto==null) {
			return null;
		}
		DepartmentEntity entity = new DepartmentEntity();
		entity.setId(dto.getId());
		entity.setDepartmentName(dto.getDepartmentName());
		entity.setCreatedAt(dto.getCreatedAt());

		return entity;
	}

	public DepartmentDTO toDTO(DepartmentEntity entity) {
		if(entity == null) {
			return null;
		}
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(entity.getId());
		dto.setDepartmentName(entity.getDepartmentName());
		dto.setCreatedAt(entity.getCreatedAt());

		return dto;
	}

}
