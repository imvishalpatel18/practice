import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TodoListRoutingModule } from './todo-list-routing.module';
import { TodoListComponent } from './todo-list.component';
import { TodoService } from '../../services/todo.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    TodoListRoutingModule
  ],
  declarations: [TodoListComponent],
  providers: [
    TodoService
  ]
})
export class TodoListModule { }
