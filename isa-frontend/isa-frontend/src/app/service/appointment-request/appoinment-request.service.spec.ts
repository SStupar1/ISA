import { TestBed } from '@angular/core/testing';

import { AppoinmentRequestService } from './appoinment-request.service';

describe('AppoinmentRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppoinmentRequestService = TestBed.get(AppoinmentRequestService);
    expect(service).toBeTruthy();
  });
});
