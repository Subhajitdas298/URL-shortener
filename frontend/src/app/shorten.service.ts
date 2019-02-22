import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {environment} from '../environments/environment';
import {URLCode} from './model/urlcode';

@Injectable({
  providedIn: 'root'
})
export class ShortenService {

  constructor(private http: HttpClient) { }

  saveUrl(url: string) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      observe: 'response' as 'response'
    };
    return this.http.post<URLCode>(environment.apiURL + '/save', new URLCode(url), httpOptions);
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
