package com.workout.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workout.api.model.Category;
import com.workout.api.repository.CategoryRepository;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CategoryService {

	private final Logger log = LoggerFactory.getLogger(CategoryService.class);

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Save a category.
	 *
	 * @param categoryDTO the entity to save
	 * @return the persisted entity
	 */
	public Category save(Category category) {
		log.debug("Request to save Category : {}", category);
		return categoryRepository.save(category);
	}

	/**
	 * Get all the categorys.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		log.debug("Request to get all Categorys");
		return categoryRepository.findAll();
	}

	/**
	 * Get one category by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<Category> findOne(String id) {
		log.debug("Request to get Category : {}", id);
		return categoryRepository.findById(id);
	}

	/**
	 * Delete the category by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete Category : {}", id);
		categoryRepository.deleteById(id);
	}

}
