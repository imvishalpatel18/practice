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

import com.workout.api.model.Category;
import com.workout.api.service.CategoryService;
import com.workout.api.web.rest.errors.BadRequestAlertException;
import com.workout.api.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

	private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

	private static final String ENTITY_NAME = "category";

	private final CategoryService categoryService;

	public CategoryResource(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * POST /categories : Create a new category.
	 *
	 * @param categoryDTO the categoryDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         categoryDTO, or with status 400 (Bad Request) if the category has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category categoryDTO) throws URISyntaxException {
		log.debug("REST request to save Category : {}", categoryDTO);
		if (categoryDTO.getId() != null) {
			throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Category result = categoryService.save(categoryDTO);
		return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /categories : Updates an existing category.
	 *
	 * @param categoryDTO the categoryDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         categoryDTO, or with status 400 (Bad Request) if the categoryDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         categoryDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/categories")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category categoryDTO) throws URISyntaxException {
		log.debug("REST request to update Category : {}", categoryDTO);
		if (categoryDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Category result = categoryService.save(categoryDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryDTO.getId().toString())).body(result);
	}

	/**
	 * GET /categories : get all the categories.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of categories in
	 *         body
	 */
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategorys() {
		log.debug("REST request to get a page of Categorys");
		return ResponseEntity.ok().body(categoryService.findAll());
	}

	/**
	 * GET /categories/:id : get the "id" category.
	 *
	 * @param id the id of the categoryDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         categoryDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable String id) {
		log.debug("REST request to get Category : {}", id);
		Optional<Category> categoryDTO = categoryService.findOne(id);
		return ResponseEntity.ok().body(categoryDTO.get());
	}

	/**
	 * DELETE /categories/:id : delete the "id" category.
	 *
	 * @param id the id of the categoryDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
		log.debug("REST request to delete Category : {}", id);
		categoryService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
