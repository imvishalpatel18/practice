import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-stop-workout-dialog',
  templateUrl: './stop-workout-dialog.component.html',
  styleUrls: ['./stop-workout-dialog.component.css']
})
export class StopWorkoutDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private passedData: any) { }

  ngOnInit() {
  }

}
