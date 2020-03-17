package com.operr.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.operr.todo.model.Todo;

/**
 * Spring Data repository for the Todo entity
 * 
 * @author vikas.patel
 *
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
