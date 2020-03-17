import { takeUntil } from 'rxjs/operators';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { WorkoutService } from '../services/workout.service';
import { Subject } from 'rxjs';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit, OnDestroy {

  isWorkoutActive = false;
  onDestroy$ = new Subject();
  tabs = ['View All', 'Create', 'Category', 'Track'];
  selected = new FormControl(0);
  constructor(private workoutService: WorkoutService) { }

  ngOnInit() {

  }

  ngOnDestroy(): void {
    this.onDestroy$.next();
  }
}
