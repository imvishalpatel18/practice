package com.operr.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.operr.todo.dto.TodoDTO;
import com.operr.todo.model.Todo;
import com.operr.todo.repository.TodoRepository;

/**
 * This class is responsible for handling all business layer logic of todo.
 * 
 * @author vikas
 *
 */
@Service
public class TodoService {

	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	/**
	 * Create or update todo.
	 * 
	 * @param label
	 * @return
	 */
	public TodoDTO save(TodoDTO label) {
		Todo todo = TodoDTO.toEntity(label);
		todo = this.todoRepository.save(todo);
		return TodoDTO.toDTO(todo);
	}

	/**
	 * Retrieve all the todo.
	 * 
	 * @return
	 */
	public List<TodoDTO> findAll() {
		return todoRepository.findAll().stream().map(TodoDTO::toDTO).collect(Collectors.toList());
	}

	/**
	 * Delete todo by id.
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		this.todoRepository.deleteById(id);
	}

	/**
	 * Find todo by id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<TodoDTO> findById(Long id) {
		return todoRepository.findById(id).map(TodoDTO::toDTO);
	}

	@Transactional
	public void deleteMultiple(List<Long> todos) {
		todos.stream().forEach(id -> {
			this.deleteById(id);
		});
	}

}
