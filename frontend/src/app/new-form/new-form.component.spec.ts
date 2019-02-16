import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFormComponent } from './new-form.component';
import {FormsModule} from '@angular/forms';
import {ShortenService} from '../shorten.service';
import {ResultModalComponent} from './result-modal/result-modal.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('NewFormComponent', () => {
  let component: NewFormComponent;
  let fixture: ComponentFixture<NewFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ FormsModule, HttpClientTestingModule ],
      declarations: [ NewFormComponent, ResultModalComponent ],
      providers: [ ShortenService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
