import { MatSnackBar } from '@angular/material';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UiService {
  progressLoadingEvent = new Subject<boolean>();

  constructor(private snackbar: MatSnackBar) {}

  showSnackbar(message: string, action: string, duration: number) {
    this.snackbar.open(message, action, { duration });
  }
}
