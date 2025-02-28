package com.example.demo.component;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
	public void updateEntity(CompanyDTO dto, CompanyEntity entity) {
		if (dto == null || entity == null) {
			return; // 'return;' in 'void' means to stop the function right away
		}
		entity.setCompanyName(dto.getName());
		entity.setCompanyAddress(dto.getAddress());
		entity.setCreateAt(dto.getCreateAt());
	}
	public CompanyEntity toEntity(CompanyDTO dto) {
		if(dto == null) {
			return null;
		}
		CompanyEntity entity = new CompanyEntity();
		entity.setCompanyId(dto.getId()); // thừa
		entity.setCompanyName(dto.getName());
		entity.setCompanyAddress(dto.getAddress());
		entity.setCreateAt(dto.getCreateAt());
		return entity;
	}

	public CompanyDTO toDTO(CompanyEntity entity) {
		if (entity ==null) {
			return null;
		}
		CompanyDTO dto = new CompanyDTO();
		dto.setId(entity.getCompanyId());
		dto.setName(entity.getCompanyName());
		dto.setAddress(entity.getCompanyAddress());
		dto.setCreateAt(entity.getCreateAt());
		return dto;
	}



}
