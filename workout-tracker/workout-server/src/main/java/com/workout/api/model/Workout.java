package com.workout.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Database entity of category table.
 * 
 * @author vishal.patel
 *
 */
@Entity
@Table(name = "workout")
public class Workout implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3463649288198967532L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@Column(name = "title")
	private String title;

	@Column(name = "note")
	private String note;

	@Column(name = "calories_burn_per_min")
	private Double caloriesBurnPerMin;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getCaloriesBurnPerMin() {
		return caloriesBurnPerMin;
	}

	public void setCaloriesBurnPerMin(Double caloriesBurnPerMin) {
		this.caloriesBurnPerMin = caloriesBurnPerMin;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
