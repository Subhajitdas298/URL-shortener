import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultModalComponent } from './result-modal.component';
import {URLCode} from '../../model/urlcode';
import {environment} from '../../../environments/environment';

describe('ResultModalComponent', () => {
  let component: ResultModalComponent;
  let fixture: ComponentFixture<ResultModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultModalComponent);
    component = fixture.componentInstance;
    component.currentRecord = new URLCode('http://testing', environment.redirectionURL + '/av');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
