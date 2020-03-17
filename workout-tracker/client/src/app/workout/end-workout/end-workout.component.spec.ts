import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EndWorkoutComponent } from './end-workout.component';

describe('StartWorkoutComponent', () => {
  let component: EndWorkoutComponent;
  let fixture: ComponentFixture<EndWorkoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EndWorkoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EndWorkoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
