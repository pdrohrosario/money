import { Component, OnInit, Input } from '@angular/core';
import {SelectDefault} from "../../model/selectDefault";

@Component({
  selector: 'app-card-transfer',
  templateUrl: './card-transfer.component.html',
  styleUrls: ['./card-transfer.component.css']
})
export class CardTransferComponent implements OnInit {

  @Input() id = 0;

  @Input() quantiaGasta = 0.0;

  @Input() data = new Date();

  @Input() tipoGasto! : SelectDefault;

  constructor() { }

  ngOnInit(): void {
  }

}
