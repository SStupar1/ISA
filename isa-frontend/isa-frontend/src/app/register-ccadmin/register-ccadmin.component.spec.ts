import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCcadminComponent } from './register-ccadmin.component';

describe('RegisterCcadminComponent', () => {
  let component: RegisterCcadminComponent;
  let fixture: ComponentFixture<RegisterCcadminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterCcadminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCcadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
