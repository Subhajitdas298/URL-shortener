import {Input} from '@angular/core';
import {URLCode} from './urlcode';

export class AddResult {
  urlCode: URLCode;
  info: string;
  status: number;

  constructor(info: string, status: number, urlCode?: URLCode) {
    this.info = info;
    this.status = status;
    this.urlCode = urlCode;
  }
}
