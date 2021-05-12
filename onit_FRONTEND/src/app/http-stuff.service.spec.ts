import { TestBed } from '@angular/core/testing';

import { HttpStuffService } from './http-stuff.service';

describe('HttpStuffService', () => {
  let service: HttpStuffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpStuffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
