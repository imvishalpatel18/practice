import { UiService } from './../../services/ui.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, OnDestroy {
  loginForm: FormGroup;
  isLoading = false;
  onDestroy$ = new Subject();

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService, private uiService: UiService) { }

  ngOnInit() {
    this.uiService.progressLoadingEvent.pipe(takeUntil(this.onDestroy$)).subscribe(isLoading => {
      this.isLoading = isLoading;
    });

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    this.onDestroy$.next();
    this.onDestroy$.unsubscribe();
  }

  onLoginFormSubmit() {
    this.uiService.progressLoadingEvent.next(true);
    this.authService.login({
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    }).subscribe(data => {
      this.uiService.progressLoadingEvent.next(false);
      this.router.navigate(['/workout']);
    });
  }
}
