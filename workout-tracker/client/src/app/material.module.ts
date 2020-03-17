import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule,
  MatDatepickerModule,
  MatInputModule,
  MatNativeDateModule,
  MatToolbarModule,
  MatCardModule,
  MatTabsModule,
  MatSidenavModule,
  MatSelectModule,
  MatListModule,
  MatProgressSpinnerModule,
  MatDialogModule,
  MatTableModule,
  MatSortModule,
  MatPaginatorModule,
  MatSnackBarModule,
} from '@angular/material';

import { CalendarModule } from 'primeng/calendar';
import { AccordionModule } from 'primeng/accordion';
import { MenuItem } from 'primeng/api';

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatToolbarModule,
    MatSidenavModule,
    MatCardModule,
    MatTabsModule,
    MatSelectModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSnackBarModule,
    CalendarModule,
    AccordionModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatToolbarModule,
    MatSidenavModule,
    MatCardModule,
    MatTabsModule,
    MatSelectModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSnackBarModule,
    CalendarModule
  ],
})
export class MaterialModule { }
