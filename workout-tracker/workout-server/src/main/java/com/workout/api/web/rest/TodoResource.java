package com.workout.api.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workout.api.dto.CategoryDTO;
import com.workout.api.service.TodoService;

/**
 * REST controller for managing todo task.
 * 
 * @author vishal.patel
 *
 */
@RestController
@RequestMapping("/api")
public class TodoResource {

	private final Logger log = LoggerFactory.getLogger(TodoResource.class);

	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	/**
	 * {@code POST  /todos} : Create a new todo.
	 *
	 * @param todo the todo to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new todo
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/todos")
	public ResponseEntity<CategoryDTO> createTodo(@Valid @RequestBody CategoryDTO todo) throws URISyntaxException {
		log.debug("REST request to save Category : {}", todo);

		CategoryDTO result = todoService.save(todo);
		return ResponseEntity.created(new URI("/api/todos/" + result.getId())).body(result);
	}

	/**
	 * {@code PUT  /todos} : Updates an existing todo.
	 *
	 * @param todo the todo to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated todo, {@code 500 (Internal Server Error)} if the todo
	 *         couldn't be updated.
	 */
	@PutMapping("/todos")
	public ResponseEntity<CategoryDTO> updateTodo(@Valid @RequestBody CategoryDTO todo) {
		log.debug("REST request to update Category : {}", todo);
		CategoryDTO result = todoService.save(todo);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * {@code GET  /todos} : get all the todos.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of todos in body.
	 */
	@GetMapping("/todos")
	public List<CategoryDTO> getAlltodos() {
		log.debug("REST request to get all todos");
		return todoService.findAll();
	}

	/**
	 * {@code GET  /todos/:id} : get the "id" todo.
	 *
	 * @param id the id of the todo to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the todo.
	 */
	@GetMapping("/todos/{id}")
	public ResponseEntity<CategoryDTO> getTodo(@PathVariable String id) {
		log.debug("REST request to get Category : {}", id);
		Optional<CategoryDTO> todo = todoService.findById(id);
		return ResponseEntity.ok().body(todo.get());
	}

	/**
	 * {@code DELETE  /todos/:id} : delete the "id" todo.
	 *
	 * @param id the id of the todo to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
		log.debug("REST request to delete Category : {}", id);
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Forwards any unmapped paths (except those containing a period) to the client
	 * {@code index.html}.
	 * 
	 * @return forward to client {@code index.html}.
	 */
	@GetMapping(value = "/**/{path:[^\\.]*}")
	public String forward() {
		return "forward:/";
	}
}
