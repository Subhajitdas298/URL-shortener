import { Component, OnInit } from '@angular/core';
import {URLCode} from '../model/urlcode';
import {ShortenService} from '../shorten.service';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-new-form',
  templateUrl: './new-form.component.html',
  styleUrls: ['./new-form.component.css']
})
export class NewFormComponent implements OnInit {

  url = '';

  public record: URLCode = new URLCode('');

  constructor(private shortenService: ShortenService) { }

  ngOnInit() {
  }

  shorten() {
    if (this.url.length > 4) {
      this.shortenService.saveUrl(this.url).subscribe(data => {
        this.record = data;
        this.record.code = environment.redirectionURL + '/' + this.record.code;
      });
    }
  }
}
