package com.workout.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workout.api.model.Workout;
import com.workout.api.repository.WorkoutRepository;

/**
 * Service Implementation for managing Workout.
 */
@Service
@Transactional
public class WorkoutService {

	private final Logger log = LoggerFactory.getLogger(WorkoutService.class);

	private final WorkoutRepository workoutRepository;

	public WorkoutService(WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
	}

	/**
	 * Save a workout.
	 *
	 * @param workoutDTO the entity to save
	 * @return the persisted entity
	 */
	public Workout save(Workout workout) {
		log.debug("Request to save Workout : {}", workout);
		return workoutRepository.save(workout);
	}

	/**
	 * Get all the workouts.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<Workout> findAll() {
		log.debug("Request to get all Workouts");
		return workoutRepository.findAll();
	}

	/**
	 * Get one workout by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<Workout> findOne(String id) {
		log.debug("Request to get Workout : {}", id);
		return workoutRepository.findById(id);
	}

	/**
	 * Delete the workout by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete Workout : {}", id);
		workoutRepository.deleteById(id);
	}

}
