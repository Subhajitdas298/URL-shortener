import { TestBed } from '@angular/core/testing';

import { ShortenService } from './shorten.service';

describe('ShortenService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShortenService = TestBed.get(ShortenService);
    expect(service).toBeTruthy();
  });
});
