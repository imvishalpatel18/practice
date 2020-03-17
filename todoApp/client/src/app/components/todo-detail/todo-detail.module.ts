import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TodoDetailRoutingModule } from './todo-detail-routing.module';
import { TodoDetailComponent } from './todo-detail.component';
import { TodoService } from '../../services/todo.service';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    TodoDetailRoutingModule,
    OwlDateTimeModule, OwlNativeDateTimeModule
  ],
  declarations: [TodoDetailComponent],
  providers: [
    TodoService
  ]
})
export class TodoDetailModule { }
