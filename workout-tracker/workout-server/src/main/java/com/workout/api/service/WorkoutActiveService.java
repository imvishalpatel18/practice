package com.workout.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workout.api.model.WorkoutActive;
import com.workout.api.repository.WorkoutActiveRepository;

/**
 * Service Implementation for managing WorkoutActive.
 */
@Service
@Transactional
public class WorkoutActiveService {

	private final Logger log = LoggerFactory.getLogger(WorkoutActiveService.class);

	private final WorkoutActiveRepository workoutActiveRepository;

	public WorkoutActiveService(WorkoutActiveRepository workoutActiveRepository) {
		this.workoutActiveRepository = workoutActiveRepository;
	}

	/**
	 * Save a workout.
	 *
	 * @param workoutDTO the entity to save
	 * @return the persisted entity
	 */
	public WorkoutActive save(WorkoutActive workout) {
		log.debug("Request to save WorkoutActive : {}", workout);
		return workoutActiveRepository.save(workout);
	}

	/**
	 * Get all the workoutActive.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<WorkoutActive> findAll() {
		log.debug("Request to get all WorkoutActives");
		return workoutActiveRepository.findAll();
	}

	/**
	 * Get one workout by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<WorkoutActive> findOne(String id) {
		log.debug("Request to get WorkoutActive : {}", id);
		return workoutActiveRepository.findById(id);
	}

	/**
	 * Delete the workout by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete WorkoutActive : {}", id);
		workoutActiveRepository.deleteById(id);
	}

}
