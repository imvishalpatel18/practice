import { HistoryComponent } from './history/history.component';
import { NgModule } from '@angular/core';
import { CurrentWorkoutComponent } from './current-workout/current-workout.component';
import { NewWorkoutComponent } from './new-workout/new-workout.component';
import { CategoryComponent } from './category/category.component';
import { WorkoutComponent } from './workout.component';
import { StopWorkoutDialogComponent } from './current-workout/stop-workout-dialog/stop-workout-dialog.component';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { SharedModule } from '../shared/shared.module';
import { WorkoutRoutingModule } from './workout-routing.module';
import { CategoryService } from './category/category.service';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StartWorkoutComponent } from './start-workout/start-workout.component';
import { WorkoutActiveService } from '../services/workout-active.service';
import { EndWorkoutComponent } from './end-workout/end-workout.component';

@NgModule({
  declarations: [
    CurrentWorkoutComponent,
    StopWorkoutDialogComponent,
    HistoryComponent,
    NewWorkoutComponent,
    CategoryComponent,
    WorkoutComponent,
    StartWorkoutComponent,
    EndWorkoutComponent
  ],

  imports: [ReactiveFormsModule, AngularFirestoreModule,
     SharedModule, WorkoutRoutingModule, BrowserAnimationsModule],
  entryComponents: [StopWorkoutDialogComponent],
  providers: [
    CategoryService,
    WorkoutActiveService
  ]
})
export class WorkoutModule { }
