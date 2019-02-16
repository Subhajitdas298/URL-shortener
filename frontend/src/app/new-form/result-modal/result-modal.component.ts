import {Component, Input, OnInit} from '@angular/core';
import {URLCode} from '../../model/urlcode';

@Component({
  selector: 'app-result-modal',
  templateUrl: './result-modal.component.html',
  styleUrls: ['./result-modal.component.css']
})
export class ResultModalComponent implements OnInit {

  show: boolean;
  currentRecord: URLCode;

  @Input()
  set record(record: URLCode) {
    this.currentRecord = record;
    this.show = true;
  }

  constructor() { }

  ngOnInit() {
    this.show = false;
  }

  closeDetails() {
    this.show = false;
  }
}
