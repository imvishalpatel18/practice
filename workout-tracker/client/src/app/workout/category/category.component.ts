import { UiService } from '../../services/ui.service';
import { NgForm, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Exercise } from '../../models/exercise.model';
import { WorkoutService } from '../../services/workout.service';
import { Component, OnInit, EventEmitter, Output, OnDestroy, ViewChild } from '@angular/core';
import { map, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { CategoryService } from './category.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Category } from './category.model';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryForm: FormGroup;
  categories: Category[];
  category: Category;
  displayedColumns: string[] = ['name', 'actions'];
  dataSource = new MatTableDataSource<Category>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private fb: FormBuilder, private router: Router,
    private categoryService: CategoryService,
    private workoutService: WorkoutService, private uiService: UiService) { }

  ngOnInit() {
    this.categoryForm = this.fb.group({
      name: ['', [Validators.required]]
    });
    this.category = new Category();
    this.loadAll();
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  loadAll() {
    this.categoryService
      .query({
      })
      .subscribe(
        (res: HttpResponse<Category[]>) => this.body(res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  createCategory() {
    if (this.category.id) {
      this.updateCategory();
      return;
    }
    this.uiService.progressLoadingEvent.next(true);

    this.category.name = this.categoryForm.value.name;

    this.category.id = this.categoryForm.value.id;
    this.categoryService.create(this.category).subscribe(data => {
      this.uiService.progressLoadingEvent.next(false);
      this.reset();
      this.loadAll();
    });
  }

  updateCategory() {
    this.uiService.progressLoadingEvent.next(true);
    this.category.name = this.categoryForm.value.name;
    this.categoryService.update(this.category).subscribe(data => {
      this.uiService.progressLoadingEvent.next(false);
      this.reset();
      this.loadAll();
    });
  }

  reset() {
    this.category = new Category();
    this.categoryForm.reset();
  }

  protected body(data: Category[]) {
    this.categories = data;
    this.dataSource.data = data;
  }

  protected onError(errorMessage: string) {

  }

  doFilter(filter: string) {
    this.dataSource.filter = filter.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onEdit(categoty: Category) {
    this.categoryService.find(categoty.id).subscribe(data => {
      this.category = data.body;
      this.categoryForm.value.name = this.category.name;
      this.categoryForm.value.id = this.category.id;
    });
  }

  onDelete(categoty: Category) {
    this.categoryService.delete(categoty.id).subscribe(data => {
      this.loadAll();
    });
  }
}
