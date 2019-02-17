import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../environments/environment';
import {URLCode} from './model/urlcode';

@Injectable({
  providedIn: 'root'
})
export class ShortenService {

  constructor(private http: HttpClient) { }

  saveUrl(url: string) {
    return this.http.post<URLCode>(environment.apiURL + '/save', new URLCode(url));
  }

  getUrlsPage(code: string, page: number, size: number) {
    const urlParams = new HttpParams()
      .set('code', code)
      .set('page', page.toString())
      .set('size', size.toString());
    const options = {params: urlParams};
    return this.http.get<any>(environment.apiURL + '/page', options);
  }
}