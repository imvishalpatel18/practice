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

import com.workout.api.model.WorkoutActive;
import com.workout.api.service.WorkoutActiveService;
import com.workout.api.web.rest.errors.BadRequestAlertException;
import com.workout.api.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Workout.
 */
@RestController
@RequestMapping("/api")
public class WorkoutResource {

	private final Logger log = LoggerFactory.getLogger(WorkoutResource.class);

	private static final String ENTITY_NAME = "workoutactive";

	private final WorkoutActiveService workoutActiveService;

	public WorkoutResource(WorkoutActiveService workoutActiveService) {
		this.workoutActiveService = workoutActiveService;
	}

	/**
	 * POST /workouts : Create a new workout.
	 *
	 * @param workoutDTO the workoutDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         workoutDTO, or with status 400 (Bad Request) if the workout has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/workoutactive")
	public ResponseEntity<WorkoutActive> createWorkoutActive(@Valid @RequestBody WorkoutActive workoutDTO) throws URISyntaxException {
		log.debug("REST request to save WorkoutActive : {}", workoutDTO);
		if (workoutDTO.getId() != null) {
			throw new BadRequestAlertException("A new workout cannot already have an ID", ENTITY_NAME, "idexists");
		}
		WorkoutActive result = workoutActiveService.save(workoutDTO);
		return ResponseEntity.created(new URI("/api/workouts/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /workouts : Updates an existing workout.
	 *
	 * @param workoutDTO the workoutDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         workoutDTO, or with status 400 (Bad Request) if the workoutDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the workoutDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/workoutactive")
	public ResponseEntity<WorkoutActive> updateWorkoutActive(@Valid @RequestBody WorkoutActive workoutDTO) throws URISyntaxException {
		log.debug("REST request to update WorkoutActive : {}", workoutDTO);
		if (workoutDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		WorkoutActive result = workoutActiveService.save(workoutDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workoutDTO.getId().toString())).body(result);
	}

	/**
	 * GET /workouts : get all the workouts.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of workouts in
	 *         body
	 */
	@GetMapping("/workoutactive")
	public ResponseEntity<List<WorkoutActive>> getAllWorkoutActives() {
		log.debug("REST request to get a page of WorkoutActives");
		return ResponseEntity.ok().body(workoutActiveService.findAll());
	}

	/**
	 * GET /workouts/:id : get the "id" workout.
	 *
	 * @param id the id of the workoutDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the workoutDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/workoutactive/{id}")
	public ResponseEntity<WorkoutActive> getWorkoutActive(@PathVariable String id) {
		log.debug("REST request to get WorkoutActive : {}", id);
		Optional<WorkoutActive> workoutDTO = workoutActiveService.findOne(id);
		return ResponseEntity.ok().body(workoutDTO.get());
	}

	/**
	 * DELETE /workouts/:id : delete the "id" workout.
	 *
	 * @param id the id of the workoutDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/workoutactive/{id}")
	public ResponseEntity<Void> deleteWorkoutActive(@PathVariable String id) {
		log.debug("REST request to delete WorkoutActive : {}", id);
		workoutActiveService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
