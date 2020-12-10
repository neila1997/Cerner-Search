import { TestBed } from '@angular/core/testing';

import { CernerAdminAuthServiceService } from './cerner-admin-auth-service.service';

describe('CernerAdminAuthServiceService', () => {
  let service: CernerAdminAuthServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CernerAdminAuthServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
