import { AuthService } from './../../services/auth.service';
import { Component, OnInit, EventEmitter, Output, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-sidenav-list',
  templateUrl: './sidenav-list.component.html',
  styleUrls: ['./sidenav-list.component.css'],
})
export class SidenavListComponent implements OnInit, OnDestroy {
  @Output() sidenavToggle = new EventEmitter<void>();

  isAuth = false;
  onDestroy$ = new Subject();

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

  onLogout() {
    this.toggleSidenav();
    this.authService.logout();
  }

  private toggleSidenav() {
    this.sidenavToggle.emit();
  }
}
