import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from '../../model/todo';
import { TodoService } from '../../services/todo.service';

@Component({
    selector: 'app-todo-list',
    templateUrl: './todo-list.component.html',
    styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {
    public todos: Todo[] = [];
    constructor(private router: Router, private todoService: TodoService) { }

    ngOnInit() {
        this.loadAllTodoList();
    }
    loadAllTodoList() {
        this.todoService.getAllTodos().subscribe(todos => {
            this.todos = todos;
        });
    }

    onClickEditTodoDetail(id) {
        console.log(id);
        this.router.navigate(['/todo-detail'], { queryParams: { id: id } });
    }

    onClickAddTodo() {
        this.router.navigate(['/todo-detail']);
    }

    onClickTodoDelete(id) {
        this.todoService.deleteTodoDetail(id).subscribe(result => {
            this.loadAllTodoList();
        });

    }

    deleteMultiple() {
        var deleteTodos = [];
        this.todos.forEach(todo => {
            if (todo.checked) {
                deleteTodos.push(todo.id);
            }
        })

        if (deleteTodos.length > 0) {
            this.todoService.deleteMultipleTodoDetail(deleteTodos).subscribe(result => {
                this.loadAllTodoList();
            });
        }


    }

}
