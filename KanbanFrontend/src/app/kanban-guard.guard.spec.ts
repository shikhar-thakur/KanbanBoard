import { TestBed } from '@angular/core/testing';

import { KanbanGuardGuard } from './kanban-guard.guard';

describe('KanbanGuardGuard', () => {
  let guard: KanbanGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(KanbanGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
