import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CcadminComponent } from './ccadmin.component';

describe('CcadminComponent', () => {
  let component: CcadminComponent;
  let fixture: ComponentFixture<CcadminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CcadminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CcadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
