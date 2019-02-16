import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

import {ShortenService} from '../shorten.service';
import {URLCode} from '../model/urlcode';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-all-list',
  templateUrl: './all-list.component.html',
  styleUrls: ['./all-list.component.css']
})
export class AllListComponent implements OnInit {

  redirectionURL: string;

  inputChanged: Subject<string> = new Subject<string>();

  urlRecords: URLCode[];

  private page: number;
  code: string;

  hasNext: boolean;
  hasPrev: boolean;

  constructor(private shortenService: ShortenService) { }

  ngOnInit() {
    this.redirectionURL = environment.redirectionURL;
    this.inputChanged.pipe(debounceTime(500)).subscribe(data => {
      this.code = data;
      this.page = 0;
      this.loadData();
    });
    this.code = '';
    this.page = 0;
    this.loadData();
  }

  loadData() {
    this.shortenService.getUrlsPage(this.code, this.page, 15).subscribe(data => {
      const obj = JSON.parse(JSON.stringify(data));
      this.urlRecords = obj.content;
      this.hasNext =  !obj.last;
      this.hasPrev = !obj.first;
    });
  }

  loadNext() {
    if (this.hasNext) {
      this.page += 1;
      this.loadData();
    }
  }

  loadPrev() {
    if ( this.hasPrev ) {
      this.page -= 1;
      this.loadData();
    }
  }

  search(event: object) {
    this.inputChanged.next(event.toString());
  }

  stopSearch() {
    this.inputChanged.next('');
  }

  refresh() {
    this.page = 0;
    this.loadData();
  }
}
