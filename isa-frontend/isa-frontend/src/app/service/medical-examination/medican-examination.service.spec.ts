import { TestBed } from '@angular/core/testing';

import { MedicanExaminationService } from './medican-examination.service';

describe('MedicanExaminationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MedicanExaminationService = TestBed.get(MedicanExaminationService);
    expect(service).toBeTruthy();
  });
});
