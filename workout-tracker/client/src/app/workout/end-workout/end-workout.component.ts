import { UiService } from '../../services/ui.service';
import { NgForm, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Exercise } from '../../models/exercise.model';
import { WorkoutService } from '../../services/workout.service';
import { Component, OnInit, EventEmitter, Output, OnDestroy } from '@angular/core';
import { map, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { CategoryService } from '../category/category.service';
import { Category } from '../category/category.model';
import { Workout } from './workout.model';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-end-workout',
  templateUrl: './end-workout.component.html',
  styleUrls: ['./end-workout.component.css'],
})
export class EndWorkoutComponent implements OnInit {
  categories: Category[] = null;
  isLoading = false;
  workout: Workout;
  workoutForm: FormGroup;
  selectedCategoryId: String;
  isEdit: Boolean = false;
  constructor(private location: Location, private fb: FormBuilder, private route: ActivatedRoute, private workoutService: WorkoutService,
    private categoryService: CategoryService, private uiService: UiService) { }

  ngOnInit() {
    this.workout = new Workout();
    const workoutId: string = this.route.snapshot.params['id'];
    if (workoutId && workoutId.length > 0) {
      this.isEdit = true;
      this.workoutService.find(workoutId).subscribe(data => {
        this.workout = data.body;
        this.selectedCategoryId = this.workout.category.id;
      });
    }


    this.workoutForm = this.fb.group({
      title: ['', [Validators.required]],
      note: [''],
      caloriesBurnPerMin: [''],
      category: ['', Validators.required]
    });


    this.categoryService.query({}).subscribe(data => {
      this.categories = data.body;
      if (this.workout.category) {
        this.workoutForm.controls.category.setValue(this.workout.category);
      }

    });
  }

  getSelectedCategory() {
    this.categories.forEach(cat => {
      if (cat.id === this.selectedCategoryId) {
        return cat;
      }
    });
    return null;
  }


  stepUp(input: HTMLInputElement): void {
    if (input.disabled) {
      return;
    }
    try {
      input.stepUp();
    } catch (ex) {
      // increment `value` by `step` (default to '1' if `step` is absent)
      input.value = String(Number(input.value) + Number(input.step || '1'));
      // if `max` is present and `value` is greater than `max`, set `value` to `max`
      if (input.max && Number(input.value) > Number(input.max)) {
        input.value = input.max;
      }
    }
  }

  // try calling stepDown() on the input element (Chrome, Firefox, Safari)
  // on failure, mimic the effect of stepDown() (Internet Explorer, Edge)
  // return immediately if input is disabled
  stepDown(input: HTMLInputElement): void {
    if (input.disabled) {
      return;
    }
    try {
      input.stepDown();
    } catch (ex) {
      // decrement `value` by `step` (default to '1' if `step` is absent)
      input.value = String(Number(input.value) - Number(input.step || '1'));
      // if `min` is present and `value` is less than `min`, set `value` to `min`
      if (input.min && Number(input.value) < Number(input.min)) {
        input.value = input.min;
      }
    }
  }



  createWorkout() {
    this.workout.category = this.getSelectedCategory();
    if (this.workout.id) {
      this.updateWorkout();
      return;
    }
    this.uiService.progressLoadingEvent.next(true);

    this.workoutService.create(this.workout).subscribe(data => {
      this.uiService.progressLoadingEvent.next(false);
      this.reset();
    });
  }

  updateWorkout() {
    this.uiService.progressLoadingEvent.next(true);
    this.workoutService.update(this.workout).subscribe(data => {
      this.uiService.progressLoadingEvent.next(false);
      this.reset();
    });
  }

  reset() {
    this.workout = new Workout();
    this.workoutForm.reset();
  }

  back() {
    this.location.back();
  }
}
