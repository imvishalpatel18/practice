import { UiService } from './../../services/ui.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { RegisterService } from './register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit, OnDestroy {
  maxDate = new Date();
  onDestroy$ = new Subject();
  isLoading = false;

  constructor(private registerService: RegisterService, private uiService: UiService, private router: Router) { }

  ngOnInit() {
    this.uiService.progressLoadingEvent.pipe(takeUntil(this.onDestroy$)).subscribe(isLoading => {
      this.isLoading = isLoading;
    });

    // must be 18 years old so set max possible picker date to today - 18 years:
    this.maxDate.setFullYear(this.maxDate.getFullYear() - 18);
  }

  ngOnDestroy(): void {
    this.onDestroy$.next();
    this.onDestroy$.unsubscribe();
  }

  onRegisterSubmit(form: NgForm) {
    this.registerService.save({
      email: form.value.email,
      password: form.value.password,
    }).subscribe(data => {
      this.router.navigate(['/login']);
    });
  }
}
