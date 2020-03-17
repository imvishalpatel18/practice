import { TestBed } from '@angular/core/testing';

import { WorkoutActiveService } from './workout-active.service';

describe('WorkoutActiveService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkoutActiveService = TestBed.get(WorkoutActiveService);
    expect(service).toBeTruthy();
  });
});
