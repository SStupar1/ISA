import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAppointmentTypesComponent } from './manage-appointment-types.component';

describe('ManageAppointmentTypesComponent', () => {
  let component: ManageAppointmentTypesComponent;
  let fixture: ComponentFixture<ManageAppointmentTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAppointmentTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAppointmentTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
