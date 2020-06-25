import { TestBed } from '@angular/core/testing';

import { WebinarRatingService } from './webinar-rating.service';

describe('WebinarRatingService', () => {
  let service: WebinarRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WebinarRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
