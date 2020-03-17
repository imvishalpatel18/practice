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

import com.workout.api.model.Workout;
import com.workout.api.service.WorkoutService;
import com.workout.api.web.rest.errors.BadRequestAlertException;
import com.workout.api.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Workout.
 */
@RestController
@RequestMapping("/api")
public class WorkoutActiveResource {

	private final Logger log = LoggerFactory.getLogger(WorkoutActiveResource.class);

	private static final String ENTITY_NAME = "workout";

	private final WorkoutService workoutService;

	public WorkoutActiveResource(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	/**
	 * POST /workoutactives : Create a new workout.
	 *
	 * @param workoutDTO the workoutDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         workoutDTO, or with status 400 (Bad Request) if the workout has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/workoutactives")
	public ResponseEntity<Workout> createWorkout(@Valid @RequestBody Workout workoutDTO) throws URISyntaxException {
		log.debug("REST request to save Workout : {}", workoutDTO);
		if (workoutDTO.getId() != null) {
			throw new BadRequestAlertException("A new workout cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Workout result = workoutService.save(workoutDTO);
		return ResponseEntity.created(new URI("/api/workoutactives/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /workoutactives : Updates an existing workout.
	 *
	 * @param workoutDTO the workoutDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         workoutDTO, or with status 400 (Bad Request) if the workoutDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the workoutDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/workoutactives")
	public ResponseEntity<Workout> updateWorkout(@Valid @RequestBody Workout workoutDTO) throws URISyntaxException {
		log.debug("REST request to update Workout : {}", workoutDTO);
		if (workoutDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Workout result = workoutService.save(workoutDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workoutDTO.getId().toString())).body(result);
	}

	/**
	 * GET /workoutactives : get all the workoutactives.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of workoutactives in
	 *         body
	 */
	@GetMapping("/workoutactives")
	public ResponseEntity<List<Workout>> getAllWorkouts() {
		log.debug("REST request to get a page of Workouts");
		return ResponseEntity.ok().body(workoutService.findAll());
	}

	/**
	 * GET /workoutactives/:id : get the "id" workout.
	 *
	 * @param id the id of the workoutDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the workoutDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/workoutactives/{id}")
	public ResponseEntity<Workout> getWorkout(@PathVariable String id) {
		log.debug("REST request to get Workout : {}", id);
		Optional<Workout> workoutDTO = workoutService.findOne(id);
		return ResponseEntity.ok().body(workoutDTO.get());
	}

	/**
	 * DELETE /workoutactives/:id : delete the "id" workout.
	 *
	 * @param id the id of the workoutDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/workoutactives/{id}")
	public ResponseEntity<Void> deleteWorkout(@PathVariable String id) {
		log.debug("REST request to delete Workout : {}", id);
		workoutService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
