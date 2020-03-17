import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Todo } from '../model/todo';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from '../app.constants';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TodoService {

    public resourceUrl = SERVER_API_URL + 'api/todos';

    public todos: Todo[] = [];
    constructor(private http: HttpClient) { }

    getAllTodos(): Observable<Todo[]> {
        return this.http.get<Todo[]>(this.resourceUrl);
    }

    getTodoById(id: number): Observable<Todo> {
        return this.http.get<Todo>(`${this.resourceUrl}/${id}`);

    }

    updateTodoById(todo): Observable<Todo> {
        return this.http.put<Todo>(this.resourceUrl, todo);
    }

    createTodoById(todo): Observable<Todo> {
        return this.http.post<Todo>(this.resourceUrl, todo);
    }

    deleteTodoDetail(id): Observable<any> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    };

    deleteMultipleTodoDetail(ids): Observable<any> {
        return this.http.post(this.resourceUrl+'/delete', ids,{ observe: 'response' });
    };
}
