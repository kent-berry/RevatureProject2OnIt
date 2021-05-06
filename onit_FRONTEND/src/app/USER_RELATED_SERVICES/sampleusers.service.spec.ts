import { TestBed } from '@angular/core/testing';

import { SAMPLEUSERSService } from './sampleusers.service';

describe('SAMPLEUSERSService', () => {
  let service: SAMPLEUSERSService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SAMPLEUSERSService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
