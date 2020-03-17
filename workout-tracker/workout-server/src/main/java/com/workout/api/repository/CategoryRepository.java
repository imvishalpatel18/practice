package com.workout.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workout.api.model.Category;

/**
 * Spring Data repository for the Category entity
 * 
 * @author vishal.patel
 *
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
