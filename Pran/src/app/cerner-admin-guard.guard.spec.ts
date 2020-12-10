import { TestBed } from '@angular/core/testing';

import { CernerAdminGuardGuard } from './cerner-admin-guard.guard';

describe('CernerAdminGuardGuard', () => {
  let guard: CernerAdminGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CernerAdminGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
