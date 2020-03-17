import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 

const routes: Routes = [
    {
        path: '',
        redirectTo: 'todo-list',
        pathMatch: 'full'
    },
    {
        path: 'todo-list',
        loadChildren: './components/todo-list/todo-list.module#TodoListModule'
    },
    {
        path: 'todo-detail',
        loadChildren: './components/todo-detail/todo-detail.module#TodoDetailModule'
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule],
    providers: []
})

export class AppRouting { }
