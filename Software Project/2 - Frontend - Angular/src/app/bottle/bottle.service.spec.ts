import { TestBed } from '@angular/core/testing';

import { BottleService } from './bottle.service';

describe('BottleService', () => {
  let service: BottleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BottleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
