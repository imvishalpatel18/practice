package com.operr.todo.dto;

import java.time.LocalDateTime;

import com.operr.todo.model.Todo;
import com.operr.todo.repository.TodoRepository;
import com.operr.todo.util.ApplicationContextProvider;

/**
 * Dto class for the Todo.
 * 
 * @author vikas.patel
 *
 */
public class TodoDTO {

	private Long id;

	private String title;

	private String description;

	private LocalDateTime eventDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public static TodoDTO toDTO(Todo entity) {
		TodoDTO dto = new TodoDTO();
		dto.setDescription(entity.getDescription());
		dto.setTitle(entity.getTitle());
		dto.setId(entity.getId());
		dto.setEventDate(entity.getEventDate());
		return dto;
	}

	public static Todo toEntity(TodoDTO dto) {

		Todo entity = new Todo();
		if (dto.getId() != null && dto.getId().longValue() > 0) {
			entity = ApplicationContextProvider.getApplicationContext().getBean(TodoRepository.class)
					.getOne(dto.getId());
		}

		entity.setDescription(dto.getDescription());
		entity.setTitle(dto.getTitle());
		entity.setId(dto.getId());
		entity.setEventDate(dto.getEventDate());
		return entity;
	}

}
