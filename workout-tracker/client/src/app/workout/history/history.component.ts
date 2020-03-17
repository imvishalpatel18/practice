import { Exercise } from './../../models/exercise.model';
import { WorkoutService } from './../../services/workout.service';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Workout } from '../new-workout/workout.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  displayedColumns: string[] = ['title', 'actions'];
  dataSource = new MatTableDataSource<Workout>();
  workouts: Workout[] = null;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router, private workoutService: WorkoutService) { }


  ngOnInit() {

    this.loadAll();
  }

  loadAll() {
    this.workoutService.query({}).subscribe(data => {
      this.workouts = data.body;
      this.dataSource.data = this.workouts;

      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  doFilter(filter: string) {
    this.dataSource.filter = filter.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onDelete(workout: Workout) {
    this.workoutService.delete(workout.id).subscribe(data => {
      this.loadAll();
    });
  }

  onEdit(workout: Workout) {
    this.router.navigate(['/workout/manage', workout.id]);
  }

}
