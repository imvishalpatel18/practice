import { AuthService } from './../../services/auth.service';
import { Component, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  isAuth = false;
  onDestroy$ = new Subject();
  @Output() sidenavToggle = new EventEmitter<void>();

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.authChange
      .asObservable()
      .pipe(takeUntil(this.onDestroy$))
      .subscribe(isLoggedIn => {
        this.isAuth = isLoggedIn;
      });
  }

  ngOnDestroy(): void {
    this.onDestroy$.next();
    this.onDestroy$.unsubscribe();
  }

  toggleSidenav() {
    this.sidenavToggle.emit();
  }

  onLogout() {
    this.authService.logout();
  }
}
