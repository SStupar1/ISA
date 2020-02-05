import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsDoctorHomePageComponent } from './patients-doctor-home-page.component';

describe('PatientsDoctorHomePageComponent', () => {
  let component: PatientsDoctorHomePageComponent;
  let fixture: ComponentFixture<PatientsDoctorHomePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientsDoctorHomePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsDoctorHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
