package com.workout.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.workout.api.dto.CategoryDTO;
import com.workout.api.model.Category;
import com.workout.api.repository.CategoryRepository;

/**
 * This class is responsible for handling all business layer logic of todo.
 * 
 * @author vishal.patel
 *
 */
@Service
public class TodoService {

	private CategoryRepository todoRepository;

	public TodoService(CategoryRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	/**
	 * Create or update todo.
	 * 
	 * @param label
	 * @return
	 */
	public CategoryDTO save(CategoryDTO label) {
		Category todo = CategoryDTO.toEntity(label);
		todo = this.todoRepository.save(todo);
		return CategoryDTO.toDTO(todo);
	}

	/**
	 * Retrieve all the todo.
	 * 
	 * @return
	 */
	public List<CategoryDTO> findAll() {
		return todoRepository.findAll().stream().map(CategoryDTO::toDTO).collect(Collectors.toList());
	}

	/**
	 * Delete todo by id.
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		this.todoRepository.deleteById(id);
	}

	/**
	 * Find todo by id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<CategoryDTO> findById(String id) {
		return todoRepository.findById(id).map(CategoryDTO::toDTO);
	}

}
