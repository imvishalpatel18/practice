import { Exercise } from './../../models/exercise.model';
import { WorkoutService } from './../../services/workout.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material';
import { StopWorkoutDialogComponent } from './stop-workout-dialog/stop-workout-dialog.component';

@Component({
  selector: 'app-current-workout',
  templateUrl: './current-workout.component.html',
  styleUrls: ['./current-workout.component.css']
})
export class CurrentWorkoutComponent implements OnInit {

  progress = 0;
  timer;
  currentExercise: Exercise;

  constructor(
    private dialog: MatDialog,
    private workoutService: WorkoutService) { }

  ngOnInit() {
    this.startOrResumeWorkout();
  }

  onStop(): void {


  }

  private startOrResumeWorkout(): void {

  }

}
