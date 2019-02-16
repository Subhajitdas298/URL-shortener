import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllListComponent } from './all-list.component';

describe('AllListComponent', () => {
  let component: AllListComponent;
  let fixture: ComponentFixture<AllListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
