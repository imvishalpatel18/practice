import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { AuthRoutingModule } from './auth-routing.module';
import { RegisterService } from './register/register.service';
import { AppRoutingModule } from '../app-routing.module';
import { WorkoutModule } from '../workout/workout.module';

@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [ReactiveFormsModule, WorkoutModule, AppRoutingModule, AngularFireAuthModule, SharedModule, AuthRoutingModule],
  providers: [
    RegisterService
  ]
})
export class AuthModule { }
