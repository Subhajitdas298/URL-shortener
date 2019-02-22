import { Component, OnInit } from '@angular/core';
import {URLCode} from '../model/urlcode';
import {ShortenService} from '../shorten.service';
import {environment} from '../../environments/environment';
import {HttpResponse} from '@angular/common/http';
import {AddResult} from '../model/add-result';

@Component({
  selector: 'app-new-form',
  templateUrl: './new-form.component.html',
  styleUrls: ['./new-form.component.css']
})
export class NewFormComponent implements OnInit {

  url = '';
  result: AddResult;

  constructor(private shortenService: ShortenService) { }

  ngOnInit() {
  }

  shorten() {
    if (this.url.length > 4) {
      this.shortenService.saveUrl(this.url).subscribe(resp => {
          this.updateModal(resp.body, resp.status);
        },
        error1 => {
          if (error1.status === 400) {
            this.updateModalError('Invalid URL', error1.status);
          } else {
            this.updateModalError(error1.error, error1.status);
          }
        });
    }
  }

  updateModal(record: URLCode, status: number) {
    record.code = environment.redirectionURL + '/' + record.code;
    // console.log(this.status);
    this.result = new AddResult('URL Shortened', status, record);
  }

  updateModalError(info: string, status: number) {
    this.result = new AddResult(info, status);
  }
}
