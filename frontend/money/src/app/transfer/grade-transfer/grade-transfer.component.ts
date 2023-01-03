import { Component, OnInit, Input } from '@angular/core';
import {Transfers} from "../transfers";
import { TransfersService } from '../transfers.service';

@Component({
  selector: 'app-grade-transfer',
  templateUrl: './grade-transfer.component.html',
  styleUrls: ['./grade-transfer.component.css']
})
export class GradeTransferComponent implements OnInit {

  @Input() transfers!:Transfers;

  constructor(private transferService:TransfersService) { }

  ngOnInit(): void {
  }

  delete(id : number): void {
    let newTransfersList = this.transfers.filter(transf => transf.id != id);
    this.transfers = newTransfersList;
    this.transferService.delete(id).subscribe((response :number) => {
      alert('Transferência removida com sucesso !');
    });
  }
}
