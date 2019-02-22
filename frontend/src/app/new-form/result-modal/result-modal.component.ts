import {Component, Input, OnInit} from '@angular/core';
import {URLCode} from '../../model/urlcode';
import {AddResult} from '../../model/add-result';

@Component({
  selector: 'app-result-modal',
  templateUrl: './result-modal.component.html',
  styleUrls: ['./result-modal.component.css']
})
export class ResultModalComponent implements OnInit {

  show: boolean;
  currentResult: AddResult;

  @Input()
  set result(result: AddResult) {
    this.currentResult = result;
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
