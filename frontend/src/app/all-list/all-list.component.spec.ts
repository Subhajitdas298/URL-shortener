import {async, ComponentFixture, getTestBed, TestBed} from '@angular/core/testing';

import { AllListComponent } from './all-list.component';
import {FormsModule} from '@angular/forms';
import {ShortenService} from '../shorten.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {URLCode} from '../model/urlcode';
import {environment} from '../../environments/environment';
import {HttpParams} from '@angular/common/http';
import {debounceTime} from 'rxjs/operators';

describe('AllListComponent', () => {
  let component: AllListComponent;
  let fixture: ComponentFixture<AllListComponent>;

  let injector: TestBed;
  let service: ShortenService;
  let httpMock: HttpTestingController;

  // dummy JSON response

  const dummyData =   {
    content: [
      {
        id: '5c681caf246d8e72c032fede',
        code: 'b',
        url: 'http://google.com',
        hitCount: 0
      }
    ],
    pageable: {
      sort: {
        sorted: false,
        unsorted: true,
        empty: true
      },
      offset: 0,
      pageSize: 1,
      pageNumber: 0,
      unpaged: false,
      paged: true
    },
    size: 1,
    number: 0,
    sort: {
      sorted: false,
      unsorted: true,
      empty: true
    },
    numberOfElements: 1,
    first: true,
    last: true,
    empty: false
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientTestingModule],
      declarations: [ AllListComponent ],
      providers: [ShortenService]
    })
    .compileComponents();
    injector = getTestBed();
    service = injector.get(ShortenService);
    httpMock = injector.get(HttpTestingController);

    fixture = TestBed.createComponent(AllListComponent);
    component = fixture.componentInstance;
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
