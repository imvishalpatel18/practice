import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StopWorkoutDialogComponent } from './stop-workout-dialog.component';

describe('StopWorkoutDialogComponent', () => {
  let component: StopWorkoutDialogComponent;
  let fixture: ComponentFixture<StopWorkoutDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StopWorkoutDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StopWorkoutDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
