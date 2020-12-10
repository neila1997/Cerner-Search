import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CernerAdminLoginComponent } from './cerner-admin-login.component';

describe('CernerAdminLoginComponent', () => {
  let component: CernerAdminLoginComponent;
  let fixture: ComponentFixture<CernerAdminLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CernerAdminLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CernerAdminLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
