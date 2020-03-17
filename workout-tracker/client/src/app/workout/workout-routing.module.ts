import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorkoutComponent } from './workout.component';
import { NewWorkoutComponent } from './new-workout/new-workout.component';
import { StartWorkoutComponent } from './start-workout/start-workout.component';
import { EndWorkoutComponent } from './end-workout/end-workout.component';

const routes: Routes = [
  { path: 'workout', component: WorkoutComponent },
  { path: 'workout/manage/:id', component: NewWorkoutComponent },
  { path: 'workout/start/:id', component: StartWorkoutComponent },
  { path: 'workout/end/:id', component: EndWorkoutComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [],
})
export class WorkoutRoutingModule {}
