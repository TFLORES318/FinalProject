import { TestBed } from '@angular/core/testing';

import { CommentRatingService } from './comment-rating.service';

describe('CommentRatingService', () => {
  let service: CommentRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommentRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
