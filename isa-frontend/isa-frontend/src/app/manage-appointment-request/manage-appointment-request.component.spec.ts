import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAppointmentRequestComponent } from './manage-appointment-request.component';

describe('ManageAppointmentRequestComponent', () => {
  let component: ManageAppointmentRequestComponent;
  let fixture: ComponentFixture<ManageAppointmentRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAppointmentRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAppointmentRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
