import { TestBed } from '@angular/core/testing';

import { UserStockJournalService } from './user-stock-journal.service';

describe('UserStockJournalService', () => {
  let service: UserStockJournalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserStockJournalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
