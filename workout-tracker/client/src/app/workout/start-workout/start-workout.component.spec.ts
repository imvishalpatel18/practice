import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartWorkoutComponent } from './start-workout.component';

describe('StartWorkoutComponent', () => {
  let component: StartWorkoutComponent;
  let fixture: ComponentFixture<StartWorkoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartWorkoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartWorkoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
