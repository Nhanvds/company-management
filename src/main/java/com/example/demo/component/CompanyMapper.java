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
		entity.setCompanyName(dto.getCompanyName());
		entity.setCompanyAddress(dto.getCompanyAddress());
		entity.setCreatedAt(dto.getCreatedAt());
	}
	public CompanyEntity toEntity(CompanyDTO dto) {
		if(dto == null) {
			return null;
		}
		CompanyEntity entity = new CompanyEntity();
		entity.setId(dto.getId()); // thừa
		entity.setCompanyName(dto.getCompanyName());
		entity.setCompanyAddress(dto.getCompanyAddress());
		entity.setCreatedAt(dto.getCreatedAt());
		return entity;
	}

	public CompanyDTO toDTO(CompanyEntity entity) {
		if (entity ==null) {
			return null;
		}
		CompanyDTO dto = new CompanyDTO();
		dto.setId(entity.getId());
		dto.setCompanyName(entity.getCompanyName());
		dto.setCompanyAddress(entity.getCompanyAddress());
		dto.setCreatedAt(entity.getCreatedAt());
		return dto;
	}



}
