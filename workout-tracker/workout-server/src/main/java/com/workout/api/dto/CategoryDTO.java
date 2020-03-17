package com.workout.api.dto;

import com.workout.api.model.Category;
import com.workout.api.repository.CategoryRepository;
import com.workout.api.util.ApplicationContextProvider;

/**
 * Dto class for the Category.
 * 
 * @author vishal.patel
 *
 */
public class CategoryDTO {

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static CategoryDTO toDTO(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setName(entity.getName());
		dto.setId(entity.getId());
		return dto;
	}

	public static Category toEntity(CategoryDTO dto) {

		Category entity = new Category();
		if (dto.getId() != null) {
			entity = ApplicationContextProvider.getApplicationContext().getBean(CategoryRepository.class)
					.findById(dto.getId()).get();
		}

		entity.setName(dto.getName());
		entity.setId(dto.getId());
		return entity;
	}

}
