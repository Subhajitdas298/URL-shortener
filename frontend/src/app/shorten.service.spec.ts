import {getTestBed, TestBed} from '@angular/core/testing';

import { ShortenService } from './shorten.service';
import {URLCode} from './model/urlcode';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {environment} from '../environments/environment';
import {HttpParams} from '@angular/common/http';

describe('ShortenService', () => {
  let injector: TestBed;
  let service: ShortenService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ShortenService]
    });
    injector = getTestBed();
    service = injector.get(ShortenService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should save url', (done) => {
    const testURL = 'http://testing.test';
    const dummyData = new URLCode(testURL, 'av', 0);
    let urlCode: URLCode;

    service.saveUrl(testURL).subscribe(data => {
      urlCode = data;
      expect(urlCode.code).toBeTruthy();
      expect(urlCode.code).not.toBe('');
      expect(urlCode.url).toBe(testURL);
      expect(urlCode.hitCount).toBe(0);
      done();
    });

    const req = httpMock.expectOne(`${environment.apiURL}/save`);
    expect(req.request.method).toBe('POST');
    req.flush(dummyData);
  });
});
