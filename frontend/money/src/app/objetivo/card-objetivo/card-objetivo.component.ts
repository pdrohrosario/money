import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-objective',
  templateUrl: './card-objetivo.component.html',
  styleUrls: ['./card-objetivo.component.css']
})
export class CardObjetivoComponent implements OnInit {

  @Input() id=0;

  @Input() quantia=0.0;

  @Input() tipoGasto = '';

  @Input() titulo = '';

  ngOnInit(): void {
  }

}
