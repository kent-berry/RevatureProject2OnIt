import { TestBed } from '@angular/core/testing';

import { SignedInUserService } from './signed-in-user.service';

describe('SignedInUserService', () => {
  let service: SignedInUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SignedInUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
