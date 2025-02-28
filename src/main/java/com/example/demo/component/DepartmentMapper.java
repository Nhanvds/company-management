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
		entity.setDepartmentName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());
	}

	public DepartmentEntity toEntity(DepartmentDTO dto){
		if(dto==null) {
			return null;
		}
		DepartmentEntity entity = new DepartmentEntity();
		entity.setDepartmentId(dto.getId());
		entity.setDepartmentName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());

		return entity;
	}

	public DepartmentDTO toDTO(DepartmentEntity entity) {
		if(entity == null) {
			return null;
		}
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(entity.getDepartmentId());
		dto.setName(entity.getDepartmentName());
		dto.setCreateAt(entity.getCreateAt());

		return dto;
	}

}
