package com.operr.todo.web.rest;

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

import com.operr.todo.dto.TodoDTO;
import com.operr.todo.service.TodoService;

/**
 * REST controller for managing todo task.
 * 
 * @author vikas
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
	public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todo) throws URISyntaxException {
		log.debug("REST request to save Todo : {}", todo);

		TodoDTO result = todoService.save(todo);
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
	public ResponseEntity<TodoDTO> updateTodo(@Valid @RequestBody TodoDTO todo) {
		log.debug("REST request to update Todo : {}", todo);
		TodoDTO result = todoService.save(todo);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * {@code GET  /todos} : get all the todos.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of todos in body.
	 */
	@GetMapping("/todos")
	public List<TodoDTO> getAlltodos() {
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
	public ResponseEntity<TodoDTO> getTodo(@PathVariable Long id) {
		log.debug("REST request to get Todo : {}", id);
		Optional<TodoDTO> todo = todoService.findById(id);
		return ResponseEntity.ok().body(todo.get());
	}

	/**
	 * {@code DELETE  /todos/:id} : delete the "id" todo.
	 *
	 * @param id the id of the todo to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
		log.debug("REST request to delete Todo : {}", id);
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * {@code POST  /todos} : Create a new todo.
	 *
	 * @param todo the todo to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new todo
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/todos/delete")
	public ResponseEntity<Void> deleteTodos(@Valid @RequestBody List<Long> todos) throws URISyntaxException {
		log.debug("REST request to delete multiple Todos : {}", todos);

		todoService.deleteMultiple(todos);
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
